package com.Jahedullah.ProjectV1.utils;

import com.Jahedullah.ProjectV1.models.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        //create configuration
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Product.class);


            //create session factory
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}