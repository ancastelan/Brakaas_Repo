package com.example.test_mspr_produit.controllers;


import com.example.test_mspr_produit.db.DbOpenHelper;
import com.example.test_mspr_produit.models.Client;
import com.example.test_mspr_produit.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    public IndexController() throws SQLException {

    }


    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {

        return "index";
    }



}
