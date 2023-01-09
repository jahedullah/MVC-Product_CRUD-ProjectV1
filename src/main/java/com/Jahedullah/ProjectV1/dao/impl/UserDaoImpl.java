package com.Jahedullah.ProjectV1.dao.impl;

import com.Jahedullah.ProjectV1.dao.UserDao;
import com.Jahedullah.ProjectV1.entity.User;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    public User findByUsername(String Username) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, Username);
        session.getTransaction().commit();
        session.close();

        return user;

    }

    @Override
    public void save(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public User findByEmail(String email) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "from User where email = :e";
        Query q = session.createQuery(query);
        q.setParameter("e", email);
        User user = (User) q.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return user;

    }


}
