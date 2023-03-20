package com.example.test_mspr_produit.models;

import lombok.Data;

@Data
public class Client {
    private int id_client;
    private String firstname;
    private String lastname;
    private String company_address;
    private String company_name;
    private String phone_number;
    private String email_address;
    private String SIRET_number;
    //private int command_id; //?????

}
