package com.example.test_mspr_produit.models;
import lombok.Data;

@Data
public class Order {

    private int id_order;
    private int client_id;
    private int product_id;
    private int client_name;
    private int product_name;
    private int quantity;
    private int total;
    private String date;
}