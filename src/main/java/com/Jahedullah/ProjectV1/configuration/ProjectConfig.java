package com.Jahedullah.ProjectV1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.Jahedullah.ProjectV1")
public class ProjectConfig {

    //set up View Resolver
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }




//    @Bean
//    public DriverManagerDataSource driverManagerDataSource(){
//
//        return driverManagerDataSource();
//    }
}
