package com.example.test_mspr_produit.controllers;

import com.example.test_mspr_produit.models.Client;
import com.example.test_mspr_produit.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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

        public ClientController() {
            clients.add(new Client()
                    .setFname("toto")
                    .setLname("toto")
                    .setId_client(1));
            clients.add(new Client().setFname("Vis").setId_client(2));
            clients.add(new Client().setFname("Boulon").setId_client(3));
            clients.add(new Client().setFname("Ecrou").setId_client(4));
        }

        @GetMapping(CommonConstant.ROUTE_ALL)
        public String showAll(Model model) {
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
                clientFinded.setFname(clientSubmit.getFname());
                clientFinded.setLname(clientSubmit.getLname());
                clientFinded.setCompany_address(clientSubmit.getCompany_address());
                clientFinded.setCompany_name(clientSubmit.getCompany_name());
                clientFinded.setPhone_number(clientSubmit.getPhone_number());
                clientFinded.setEmail_address(clientSubmit.getEmail_address());
                clientFinded.setSIRET_number(clientSubmit.getSIRET_number());
            }

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
//      // Use For
//      for (int i = 0; i <= products.size() - 1; i++) {
//      	Product Product = products.get(i);
//      	if (Product.getId() == id) {
//              userFinded = Product;
//              break;
//          }
//		}
//      // Use Lambda
//      products.stream().findFirst(e => e.getId() == id);

            return clientFinded;
        }

}
