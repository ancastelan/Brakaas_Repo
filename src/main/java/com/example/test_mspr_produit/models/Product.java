package com.example.test_mspr_produit.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class Product {
    private int id_product;
    private String name;
    private String availability;
    private Integer price;
    private String stock;
}
