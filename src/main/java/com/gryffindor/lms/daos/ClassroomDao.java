package com.gryffindor.lms.daos;

import com.gryffindor.lms.exceptions.CustomException;
import com.gryffindor.lms.models.Classroom;
import com.gryffindor.lms.models.EnrolledClass;
import com.gryffindor.lms.models.User;
import com.gryffindor.lms.settings.HibernateUtil;
import com.gryffindor.lms.settings.StringEnums;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@Repository
@Transactional
public class ClassroomDao {

    private Session session = HibernateUtil.getInstance().getSession();

    public void save(Classroom classroom) {
       // session = HibernateUtil.getSession();
        User user = session.get(User.class, classroom.getCreatorName());
        if (user.getType().equals(StringEnums.TEACHER.getValue())) {
            System.out.println(classroom.getClassUsername());
            Transaction transaction = session.beginTransaction();
            session.save(classroom);
            transaction.commit();
        } else {
            throw new CustomException("Invalid User Type");
        }
    }

    public void joinClass(EnrolledClass enrolledClass) {
        //session = HibernateUtil.getSession();
        try{
            Criteria criteria = session.createCriteria(Classroom.class);
            criteria.add(Restrictions.eq("invitation_code", enrolledClass.getInvitationCode()));
            Classroom classroom = (Classroom) criteria.list().get(0);
            System.out.println(classroom.toString());
            enrolledClass.setClassUserName(classroom.getClassUsername());
            Transaction transaction = session.beginTransaction();
            session.save(enrolledClass);
            transaction.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
            //throw new CustomException("Can't Join this class");
        }
    }




}
