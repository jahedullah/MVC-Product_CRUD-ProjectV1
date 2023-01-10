package com.Jahedullah.ProjectV1.model.dao;

import com.Jahedullah.ProjectV1.model.entity.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Component
public interface UserDao {
    User findByUsername(String username);

    void save(User user);

    User findByEmail(String email);

    List findAllEmail();

    void deleteByEmail(String email);
    String buyProductByID(int id, HttpServletRequest request);
    void updateUserProductList(User user, Product product);
}
