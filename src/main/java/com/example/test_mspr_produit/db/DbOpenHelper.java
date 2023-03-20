package com.example.test_mspr_produit.db;

import com.example.test_mspr_produit.models.Client;

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

    public void del_xx(int id) {
        String sql_req = "DELETE FROM client WHERE id = ?";
        try {
            PreparedStatement statement = cnx.prepareStatement(sql_req);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}