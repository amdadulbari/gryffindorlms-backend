package com.gryffindor.lms.daos;

import com.gryffindor.lms.models.EnrolledClass;
import com.gryffindor.lms.models.PendingUser;
import com.gryffindor.lms.models.User;
import com.gryffindor.lms.settings.HibernateUtil;
import com.gryffindor.lms.settings.StringEnums;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@Repository
@Transactional
public class UserDao {

    Session session = HibernateUtil.getInstance().getSession();


    public boolean save(User user) {
        if (!isExist(user)) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
        return false;
    }

    public boolean isExist(User user) {
        Criteria criteria = session.createCriteria(User.class);
        System.out.println(user.getUsername());
        criteria.add(Restrictions.eq("password", user.getPassword()));
        if (criteria.list().size() == 0) {
            System.out.println("list size = " + criteria.list().size());
            return false;
        }
        return true;
    }

    public User login(String username, String password) {
        User user = null;
        try {
            user = session.get(User.class, username);
            session.close();
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    public boolean savePendingUser(PendingUser pendingUser) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(pendingUser);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public boolean verifyUser(PendingUser pendingUser) {
        PendingUser pendingUserObj = session.get(PendingUser.class, pendingUser.getUsername());
        if (pendingUserObj.getApprovalCode() == pendingUser.getApprovalCode()) {
            changeStatus(pendingUser);
            Transaction transaction = session.beginTransaction();
            session.delete(pendingUser);
            transaction.commit();
            session.close();
            return true;
        } else {
            return false;
        }
    }

    public void changeStatus(PendingUser pendingUser) {
        try {
            User user = session.get(User.class, pendingUser.getUsername());
            System.out.println(user.getUsername() + user.getStatus());
            user.setStatus(StringEnums.ACTIVE.getValue());
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getJoinedClass(String username){
        System.out.println(username);
        ClassroomDao classroomDao = new ClassroomDao();
        Query query = session.createQuery("select classUserName from EnrolledClass where studentUserName = :userName ");
        query.setParameter("userName",username);
        System.out.println("Query = "+query.toString());
        List<String> classList = query.list();
        return classList;
    }


}
