package com.Jahedullah.ProjectV1.model.dao.impl;

import com.Jahedullah.ProjectV1.model.dao.ProductDao;
import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    //creating Products here
    public void createProduct(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    //get all Products
    public List<Product> getProducts() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Product.class);
        List<Product> productList = new ArrayList<Product>(cr.list());

        session.getTransaction().commit();
        session.close();

        return productList;


    }

    //Deleting the Product
    public void deleteProduct(int pid) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Product product = session.get(Product.class, pid);
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();

    }

    //get the Single Product
    public Product getProduct(int pid) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, pid);
        session.getTransaction().commit();
        session.close();

        return product;
    }

    //update product
    public void updateProduct(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }


}
