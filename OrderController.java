package com.example.test_mspr_produit.controllers;

import com.example.test_mspr_produit.db.DbOpenHelper;
import com.example.test_mspr_produit.models.Client;
import com.example.test_mspr_produit.models.Product;
import com.example.test_mspr_produit.models.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    public static final String ORDER_MODEL = "current_order";
    public static final String ORDERS_MODEL = "orders";

    private ArrayList<Order> orders = new ArrayList<>();

    public OrderController() {

    }

    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {
        try {
            DbOpenHelper order = new DbOpenHelper();
            this.orders = order.show_all_order();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute(ORDERS_MODEL, orders);
        return "orders/order_list";
    }

    @GetMapping(CommonConstant.ROUTE_SHOW)
    public String viewOrderSheet(Model model, @PathVariable("id") long id_order) {
        Order orderFinded = this.findOrderById(id_order);
        if (orderFinded != null) {
            model.addAttribute(ORDER_MODEL, orderFinded);
        }
        return "orders/fiche_order";
    }

    @GetMapping(CommonConstant.ROUTE_CREATE)
    public String createClient(Model model) {
        Order new_order = new Order();
        model.addAttribute(ORDER_MODEL, new_order);

        return "orders/form_order_create";
    }

    @PostMapping(CommonConstant.ROUTE_SUBMIT)
    public String submitClient(Model model, @ModelAttribute Order orderSubmit) {

        DbOpenHelper DbHelper = new DbOpenHelper();
        DbHelper.create_order(orderSubmit);
        return "redirect:/orders/";
    }

    private Order findOrderById(long id) {
        Order orderFinded = null;

        for (Order order : this.orders) {
            if (order.getId_order() == id) {
                orderFinded = order;
                break;
            }
        }
        return orderFinded;
    }
}
