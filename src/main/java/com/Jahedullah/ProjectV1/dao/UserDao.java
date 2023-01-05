package com.Jahedullah.ProjectV1.dao;
import com.Jahedullah.ProjectV1.entity.*;

public interface UserDao {
        public User findByUsername(String username);

        void save(User user);

        User findByEmail(String email);
}
