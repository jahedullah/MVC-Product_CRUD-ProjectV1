package com.Jahedullah.ProjectV1.model.dao.impl;

import com.Jahedullah.ProjectV1.model.dao.ProductDao;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductRegisterResponseDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductUpdateRequestDto;
import com.Jahedullah.ProjectV1.model.dto.Product.ProductDto;
import com.Jahedullah.ProjectV1.model.entity.Product;
import com.Jahedullah.ProjectV1.model.entity.User;
import com.Jahedullah.ProjectV1.utils.HibernateUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public ProductDto updateProduct(int productId, ProductUpdateRequestDto productUpdateRequestDto) {

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
        return ProductDto.builder()
                .id(productToUpdate.getId())
                .name(productToUpdate.getName())
                .description(productToUpdate.getDescription())
                .price(productToUpdate.getPrice())
                .productCount(productToUpdate.getProductCount())
                .build();
    }

    // get the Single Product
//    @Override
//    public ProductUpdateResponseDto getProduct(int productId) {
//
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        session.beginTransaction();
//        Product product = session.get(Product.class, productId);
//        session.getTransaction().commit();
//        session.close();
//
//        return ProductUpdateResponseDto.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .productCount(product.getProductCount())
//                .build();
//
//    }

    @Override
    public Product getProduct(int productId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, productId);
        session.getTransaction().commit();
        session.close();

        return product;
    }

    //get all Products
    public List<ProductDto> getProducts() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        Query<Product> query = session.createQuery(criteriaQuery);
        List<Product> productList = query.getResultList();
        List<ProductDto> newProductList = new ArrayList<>();
        productList.forEach(
                (tempProduct) -> {
                    ProductDto productUpdateResponseDto
                            = ProductDto.builder()
                            .id(tempProduct.getId())
                            .name(tempProduct.getName())
                            .description(tempProduct.getDescription())
                            .price(tempProduct.getPrice())
                            .productCount(tempProduct.getProductCount())
                            .build();
                    newProductList.add(productUpdateResponseDto);
                }
        );
        session.close();

        return newProductList;

    }

    //Deleting the Product
    public ProductDto deleteProduct(int pid) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Product product = session.get(Product.class, pid);
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productCount(product.getProductCount())
                .build();

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
