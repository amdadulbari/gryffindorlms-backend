package com.gryffindor.lms.settings;

import com.gryffindor.lms.models.Classroom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

public class HibernateUtil {

    Session session;
    private static HibernateUtil hibernateUtil;

    private HibernateUtil() {

    }
    public static HibernateUtil getInstance(){
        if (hibernateUtil==null){
            hibernateUtil = new HibernateUtil();
        }
        return hibernateUtil;
    }

    public static SessionFactory getConfiguration() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Classroom.class);
        return configuration.buildSessionFactory();
    }

    public Session getSession() {
        session = getConfiguration().openSession();
        return session;
    }
}
