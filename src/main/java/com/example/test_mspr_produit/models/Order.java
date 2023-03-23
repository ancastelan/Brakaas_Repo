package com.example.test_mspr_produit.models;
import lombok.Data;

@Data
public class Order {

    private int id_order;
    private int client_id;
    private String client_name;
    private int product_id;
    private String product_name;
    private int quantity;
    private int total;
    private String date;
}