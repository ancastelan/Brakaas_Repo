package com.example.test_mspr_produit.controllers;

import com.example.test_mspr_produit.db.DbOpenHelper;
import com.example.test_mspr_produit.models.Client;
import com.example.test_mspr_produit.models.Product;
import com.example.test_mspr_produit.models.Order;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrderController {

    public static final String ORDER_MODEL = "current_order";
    public static final String ORDERS_MODEL = "orders";
    public static final String PRODUCTS_MODEL = "products";

    private ArrayList<Integer> id_products = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private static Integer AUTO_INCR_ORDER = 56;

    public OrderController() {

    }

    public void setAUTO_INCR_ORDER(Integer incr) {
        this.AUTO_INCR_ORDER = incr;
    }

    public Integer getAUTO_INCR_ORDER() {
        return this.AUTO_INCR_ORDER;
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

    @GetMapping(CommonConstant.ROUTE_CREATE_ORDER)
    public String createOrder(Model model, @PathVariable("id") long id_client) {
        try {
            DbOpenHelper order_dbo = new DbOpenHelper();
            this.products = order_dbo.show_all_product();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("id_products", id_products);
        model.addAttribute(PRODUCTS_MODEL, products);
        return "orders/form_order_create";
    }

    @PostMapping(CommonConstant.ROUTE_SUBMIT_ORDER)
    public String submitOrder(Model model, HttpServletRequest request, @PathVariable("id") Integer id_client) {
        DbOpenHelper DbHelper = new DbOpenHelper();
        for (Product product: products) {
            String quantityParamName = Integer.toString(product.getId_product());
            int quantity = Integer.parseInt(request.getParameter(quantityParamName));
            if (quantity > 0) {
                id_products.add(product.getId_product());
                DbHelper.create_order(AUTO_INCR_ORDER, id_client, product.getId_product(), quantity);
            }
        }
        System.out.println(id_products);
        AUTO_INCR_ORDER = AUTO_INCR_ORDER + 1;

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