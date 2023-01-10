package com.Jahedullah.ProjectV1.model.dao.impl;

import com.Jahedullah.ProjectV1.model.dao.ProductDao;
import com.Jahedullah.ProjectV1.model.dao.UserDao;
import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.model.entity.User;
import com.Jahedullah.ProjectV1.model.service.JwtService;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private JwtService jwtService;
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

    @Override
    public List findAllEmail() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "select email from User";
        Query q = session.createQuery(query);
        ArrayList<String> emailList = (ArrayList<String>) q.list();
        session.getTransaction().commit();
        session.close();

        return emailList;
    }

    @Override
    public void deleteByEmail(String email) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User user = findByEmail(email);
        int userId = user.getId();
        User userToDelete = session.get(User.class, userId);
        session.beginTransaction();
        session.delete(userToDelete);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void updateUserProductList(User user, Product product){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        user.getProductList().add(product);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String buyProductByID(int id, HttpServletRequest request) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Product product = productDao.getProduct(id);
        String accessToken = request.getHeader("Authorization");
        if(accessToken != null){
            String jwt = accessToken.substring(7);
            String userEmail = jwtService.extractUsername(jwt);
            session.beginTransaction();
            User user = findByEmail(userEmail);
            updateUserProductList(user, product);
            productDao.updateProductCount(product);
            productDao.updateProductUserList(product,user);
            session.getTransaction().commit();
            session.close();

            return "Product " + id + " has been added to User.";
        }else {

            return "No Authentication Token Found. :(";
        }

    }


}
