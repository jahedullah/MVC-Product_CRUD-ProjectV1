//package com.Jahedullah.ProjectV1.dao.impl;
//import com.Jahedullah.ProjectV1.dao.SignUpDao;
//import com.Jahedullah.ProjectV1.dto.SignUpDTO;
//import com.Jahedullah.ProjectV1.entity.User;
//import com.Jahedullah.ProjectV1.utils.HibernateUtils;
//import org.hibernate.Session;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class SignUpDaoImpl implements SignUpDao{
//    @Override
//    public void saveUser(SignUpDTO signUpDTO) {
//
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        User user = new User(signUpDTO.getUsername(),
//                             signUpDTO.getPassword(),
//                            true,
//                            "USER");
//
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();
//
//    }
//}
