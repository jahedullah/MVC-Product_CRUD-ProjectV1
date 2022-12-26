package com.Jahedullah.ProjectV1.Dao;

import com.Jahedullah.ProjectV1.models.PostmanProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostmanProductDao {

    List<PostmanProduct> list;

    public PostmanProductDao(){
        list = new ArrayList<>();
        list.add(new PostmanProduct(100,"Ipod","Apple"));
        list.add(new PostmanProduct(102,"Samsung Buds","Samsung"));
    }

    public List<PostmanProduct> getProducts(){
        return list;
    }

}
