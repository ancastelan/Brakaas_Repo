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
@RequestMapping("/clients")
public class ClientController {

    public static final String CLIENT_MODEL = "current_client";
    public static final String CLIENTS_MODEL = "clients";

    //private Product currentUser;
    private ArrayList<Client> clients = new ArrayList<>();

    public ClientController() throws SQLException {

    }

    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {
        try {
            DbOpenHelper DbHelper = new DbOpenHelper();
            this.clients = DbHelper.show_all_client();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute(CLIENTS_MODEL, clients);

        return "clients/client_list";
    }


    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /products/120/show
    //@RequestMapping(method = RequestMethod.GET, path = CommonConstant.ROUTE_SHOW)	// Base of @GetMapping(...)
    public String viewClientSheet(Model model, @PathVariable("id") long id) {
        Client clientFinded = this.findClientById(id);

        if (clientFinded != null) {
            model.addAttribute(CLIENT_MODEL, clientFinded);
        }
        return "clients/fiche_client";
    }

    @GetMapping(CommonConstant.ROUTE_DELETE)
    public String delClient(Model model, @PathVariable("id") long id_client) {
        Client clientFinded = this.findClientById(id_client);

        model.addAttribute(CLIENT_MODEL, clientFinded);
        DbOpenHelper DbHelper = new DbOpenHelper();
        DbHelper.del_client(clientFinded);
        return "redirect:/clients/";
    }

    @GetMapping(CommonConstant.ROUTE_CREATE)
    public String createClient(Model model) {
        Client new_cli = new Client();
        model.addAttribute(CLIENT_MODEL, new_cli);

        return "clients/form_client_create";
    }

    @PostMapping(CommonConstant.ROUTE_SUBMIT)
    public String submitClient(Model model, @ModelAttribute Client clientSubmit) {

        DbOpenHelper DbHelper = new DbOpenHelper();
        DbHelper.create_client(clientSubmit);
        return "redirect:/clients/";
    }

    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editClient(Model model, @PathVariable("id") long id_client) {
        Client clientFinded = this.findClientById(id_client);

        model.addAttribute(CLIENT_MODEL, clientFinded);

        return "clients/form_client";
    }

    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveClient(Model model, @ModelAttribute Client clientSubmit) {
        Client clientFinded = this.findClientById(clientSubmit.getId_client());

        if (clientFinded != null) {
            clientFinded.setFirstname(clientSubmit.getFirstname());
            clientFinded.setLastname(clientSubmit.getLastname());
            clientFinded.setCompany_address(clientSubmit.getCompany_address());
            clientFinded.setCompany_name(clientSubmit.getCompany_name());
            clientFinded.setPhone_number(clientSubmit.getPhone_number());
            clientFinded.setEmail_address(clientSubmit.getEmail_address());
            clientFinded.setSIRET_number(clientSubmit.getSIRET_number());
        }
        DbOpenHelper DbHelper = new DbOpenHelper();
        DbHelper.update_client(clientSubmit);
        return "redirect:/clients/" + clientFinded.getId_client() + "/show";
    }


    private Client findClientById(long id_client) {
        Client clientFinded = null;
        // Foreach
        for (Client client : this.clients) {
            if (client.getId_client() == id_client) {
                clientFinded = client;
                break;
            }
        }
        return clientFinded;
    }
}
