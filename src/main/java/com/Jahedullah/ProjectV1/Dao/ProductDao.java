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

        return cr.list();


    }
//    //Deleting the Product
//    public void deleteProduct(int pid){
//        this.hibernateTemplate.delete(pid);
//
//    }
//
//    //get the Single Product
//    public Product getProduct(int pid){
//        return this.hibernateTemplate.get(Product.class, pid);
//    }


}
