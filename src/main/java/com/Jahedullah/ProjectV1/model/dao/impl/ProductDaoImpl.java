package com.Jahedullah.ProjectV1.model.dao.impl;

import com.Jahedullah.ProjectV1.model.dao.ProductDao;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterResponseDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateResponseDto;
import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.model.entity.User;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {


    //creating Products here
    public ProductRegisterResponseDto createProduct(ProductRegisterRequestDto
                                                            productRegisterRequestDto) {
        List productNameList = findAllProductName();
        if (!productNameList.contains(productRegisterRequestDto.getName())) {
            Product product = Product.builder()
                    .name(productRegisterRequestDto.getName())
                    .description(productRegisterRequestDto.getDescription())
                    .price(productRegisterRequestDto.getPrice())
                    .productCount(productRegisterRequestDto.getProductCount())
                    .build();

            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();
            return ProductRegisterResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .productCount(product.getProductCount())
                    .build();
        }
        return ProductRegisterResponseDto.builder()
                .build();
    }

    //update product
    public ProductUpdateResponseDto updateProduct(int productId, ProductUpdateRequestDto productUpdateRequestDto) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Product productToUpdate = session.get(Product.class, productId);
        if(!(productUpdateRequestDto.getName() == null)) {
            productToUpdate.setName(productUpdateRequestDto.getName());
        }
        if(!(productUpdateRequestDto.getDescription() == null)) {
            productToUpdate.setDescription(productUpdateRequestDto.getDescription());
        }
        if(!(productUpdateRequestDto.getPrice() == 0)) {
            productToUpdate.setPrice(productUpdateRequestDto.getPrice());
        }
        if(!(productUpdateRequestDto.getProductCount() == 0)) {
            productToUpdate.setProductCount(productUpdateRequestDto.getProductCount());
        }
        session.beginTransaction();
        session.update(productToUpdate);
        session.getTransaction().commit();
        session.close();
        return ProductUpdateResponseDto.builder()
                .name(productToUpdate.getName())
                .description(productToUpdate.getDescription())
                .price(productToUpdate.getPrice())
                .productCount(productToUpdate.getProductCount())
                .build();
    }

    // get the Single Product
    @Override
    public ProductUpdateResponseDto getProduct(int pid) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, pid);
        session.getTransaction().commit();
        session.close();

        return ProductUpdateResponseDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productCount(product.getProductCount())
                .build();

    }

    //get all Products
    public List<Product> getProducts() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "select id, name, price, productCount from Product";
        Query q = session.createQuery(query);
        ArrayList<Product> productList = (ArrayList<Product>) q.list();
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


    public void updateProductCount(Product product) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        product.setProductCount(product.getProductCount() - 1);
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateProductUserList(Product product, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        product.getUserList().add(user);
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    public List findAllProductName() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "select name from Product";
        Query q = session.createQuery(query);
        ArrayList<String> productNameList = (ArrayList<String>) q.list();
        session.getTransaction().commit();
        session.close();

        return productNameList;
    }


}
