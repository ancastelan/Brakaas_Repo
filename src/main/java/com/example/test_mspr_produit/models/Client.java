package com.example.test_mspr_produit.models;

import lombok.Data;

@Data
public class Client {
    private int id_client;
    private String fname;
    private String lname;
    private String company_address;
    private String company_name;
    private String phone_number;
    private String email_address;
    private int SIRET_number;
    private int command_id; //?????

}
