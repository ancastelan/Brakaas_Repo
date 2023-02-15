package com.example.test_mspr_produit.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class Product {
    private int id;
    private String name;
    private Integer quantity;
    private Boolean dispo;


}

