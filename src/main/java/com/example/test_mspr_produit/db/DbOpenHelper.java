package com.example.test_mspr_produit.db;

import com.example.test_mspr_produit.models.Client;
import com.example.test_mspr_produit.models.Product;
import com.example.test_mspr_produit.models.Order;

import java.sql.*;
import java.util.ArrayList;

public class DbOpenHelper {
    public static DbOpenHelper instance = null;
    private Connection cnx = null;

    public DbOpenHelper() {
        String cnxStr = "jdbc:mariadb://127.0.0.1:3306/mspr";
        try {
            this.cnx = DriverManager.getConnection(cnxStr, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCnx() {
        return this.cnx;
    }

    public static DbOpenHelper getInstance() {
        if (DbOpenHelper.instance == null) {
            synchronized (DbOpenHelper.class) {
                if (DbOpenHelper.instance == null) {
                    DbOpenHelper.instance = new DbOpenHelper();
                }
            }
        }
        return instance;
    }

    public void add_user(String firstname, String lastname, String login, String password) {
        String sql_req = "INSERT INTO users(login, password) VALUES (?, ?)";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_user(int id, String firstname, String lastname, String login, String password) {
        String sql_req = "UPDATE `users` SET `firstname` = ?, `lastname` = ?, `login` = ?, `password` = ? WHERE `users`.`id_users` = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void show_client(int id) {
        String sql_req = "SELECT * FROM `client` WHERE id = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
                                CLIENT
     */
    public void create_client(Client client){
        String sql_req = "INSERT INTO `client`(firstname, lastname, company_address, company_name, phone_number, email_address, SIRET_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setString(1, client.getFirstname());
            statement.setString(2, client.getLastname());
            statement.setString(3, client.getCompany_address());
            statement.setString(4, client.getCompany_name());
            statement.setString(5, client.getPhone_number());
            statement.setString(6, client.getEmail_address());
            statement.setString(7, client.getSIRET_number());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void del_client(Client client) {
        String sql_req = "DELETE FROM client WHERE `client`.`id_client` = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setInt(1, client.getId_client());
            statement.executeUpdate();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update_client(Client client) {
        String sql_req = "UPDATE `client` SET `firstname` = ?, `lastname` = ?, `company_address` = ?, `company_name` = ?, `phone_number` = ?, `email_address` = ?, `SIRET_number` = ? WHERE `client`.`id_client` = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setString(1, client.getFirstname());
            statement.setString(2, client.getLastname());
            statement.setString(3, client.getCompany_address());
            statement.setString(4, client.getCompany_name());
            statement.setString(5, client.getPhone_number());
            statement.setString(6, client.getEmail_address());
            statement.setString(7, client.getSIRET_number());
            statement.setInt(8, client.getId_client());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList show_all_client() throws SQLException {
        try (Statement statement = cnx.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from client");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            ArrayList<Client> a_client = new ArrayList<>();
            while (resultSet.next()) {
                Client le_client = new Client();
                ArrayList<String> carac_client = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = resultSet.getString(i);
                    carac_client.add(columnValue);
                }
                le_client.setId_client(Integer.parseInt(carac_client.get(0)))
                         .setFirstname(carac_client.get(1))
                         .setLastname(carac_client.get(2))
                         .setCompany_address(carac_client.get(3))
                         .setCompany_name(carac_client.get(4))
                         .setPhone_number(carac_client.get(5))
                         .setEmail_address(carac_client.get(6))
                         .setSIRET_number((carac_client.get(7)));
                a_client.add(le_client);
            }

            return a_client;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cnx.close();
        return null ;

    }

    /*
                                PRODUCT
     */
    public ArrayList show_all_product() throws SQLException {
        try (Statement statement = cnx.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from products");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            ArrayList<Product> a_produit = new ArrayList<>();
            while (resultSet.next()) {
                Product le_produit = new Product();
                ArrayList<String> carac_produit = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = resultSet.getString(i);
                    carac_produit.add(columnValue);
                }
                le_produit.setId_product(Integer.parseInt(carac_produit.get(0)))
                        .setName(carac_produit.get(1))
                        .setAvailability(carac_produit.get(2))
                        .setPrice(Integer.parseInt(carac_produit.get(3)))
                        .setStock(carac_produit.get(4));
                a_produit.add(le_produit);
            }

            return a_produit;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cnx.close();
        return null ;
    }
    public void update_product(Product product) {
        String sql_req = "UPDATE `products` SET `name` = ?, `availability` = ?, `price` = ?, `stock` = ? WHERE `products`.`id_product` = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setString(1, product.getName());
            statement.setString(2, product.getAvailability());
            statement.setInt(3, product.getPrice());
            statement.setString(4, product.getStock());
            statement.setInt(5, product.getId_product());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void del_product(Product product) {
        String sql_req = "DELETE FROM products WHERE `products`.`id_product` = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setInt(1, product.getId_product());
            statement.executeUpdate();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*
                                ORDER
     */


    public void create_order(Integer id_order, Integer id_du_client, Integer id_du_produit, Integer quantity) {
        String sql_req = "INSERT INTO ordered_item(id_order, client_id, client_name, product_id, product_name, quantity, total, date) VALUES (?, ?,(SELECT lastname FROM client WHERE `id_client` = ?), ?, (SELECT name FROM products WHERE `id_product` = ?), ?, (SELECT price from products WHERE id_product = ?)*?, NOW())";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setInt(1, id_order);
            statement.setInt(2, id_du_client);
            statement.setInt(3, id_du_client);
            statement.setInt(4, id_du_produit);
            statement.setInt(5, id_du_produit);
            statement.setInt(6, quantity);
            statement.setInt(7, id_du_produit);
            statement.setInt(8, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList show_all_order() throws SQLException {
        try (Statement statement = cnx.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from ordered_item");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            ArrayList<Order> a_order = new ArrayList<>();
            while (resultSet.next()) {
                Order le_order = new Order();
                ArrayList<String> carac_order = new ArrayList<>();
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    if (columnName == "client_name" || columnName == "product_name") {
                        carac_order.add(columnValue.toString());
                    }
                    carac_order.add(columnValue);
                }

                le_order.setId_order(Integer.parseInt(carac_order.get(1)))
                        .setClient_id(Integer.parseInt(carac_order.get(2)))
                        .setClient_name(carac_order.get(3))
                        .setProduct_id(Integer.parseInt(carac_order.get(4)))
                        .setProduct_name(carac_order.get(5))
                        .setQuantity(Integer.parseInt(carac_order.get(6)))
                        .setTotal(Integer.parseInt(carac_order.get(7)))
                        .setDate(carac_order.get(8));
                a_order.add(le_order);
            }

            return a_order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cnx.close();
        return null ;
    }



}