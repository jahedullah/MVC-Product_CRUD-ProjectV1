package com.Jahedullah.ProjectV1.controllers;

import com.Jahedullah.ProjectV1.Dao.ProductDao;
import com.Jahedullah.ProjectV1.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FrontController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public String showHomePage(Model model){

        List<Product> productsList = productDao.getProducts();
        model.addAttribute("products", productsList);
        return "home-page";
    }

    @RequestMapping("/add-product")
    public  String addProduct(){
        return "add-product-form";
    }

    @RequestMapping(value = "/handle-product" , method = RequestMethod.POST)
    public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request){
        System.out.println(product);
        productDao.createProduct(product);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(request.getContextPath() + "/");
        return redirectView;
    }
}
