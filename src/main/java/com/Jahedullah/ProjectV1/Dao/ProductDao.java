package com.Jahedullah.ProjectV1.Dao;

import com.Jahedullah.ProjectV1.models.Product;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {



    //creating Products here

    public void createProduct(Product product){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    //get all Products
    public List<Product> getProducts(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Product.class);
        List<Product> productList = new ArrayList<Product>(cr.list());

        session.getTransaction().commit();
        session.close();

        return productList;


    }
    //Deleting the Product
    public void deleteProduct(int pid){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Product product = session.get(Product.class,pid);
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();

    }

    //get the Single Product
    public Product getProduct(int pid){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class,pid);
        session.getTransaction().commit();
        session.close();

        return product;
    }

    //update product
    public void updateProduct(Product product){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }


}
