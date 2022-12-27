package com.Jahedullah.ProjectV1.dao.impl;

import com.Jahedullah.ProjectV1.dao.UserDao;

import com.Jahedullah.ProjectV1.models.User;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {


    public User findByUsername(String Username){

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, Username);
        session.getTransaction().commit();
        session.close();

        return user;

    }
}
