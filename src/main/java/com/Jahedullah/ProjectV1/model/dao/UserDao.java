package com.Jahedullah.ProjectV1.model.dao;

import com.Jahedullah.ProjectV1.model.entity.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public interface UserDao {
    User findByUsername(String username);

    void save(User user);

    User findByEmail(String email);

    List findAllEmail();

    void deleteByEmail(String email);
}
