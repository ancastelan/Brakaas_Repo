package com.example.test_mspr_produit;

import com.example.test_mspr_produit.db.DbOpenHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication
public class TestMsprProduitApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(TestMsprProduitApplication.class, args);
		DbOpenHelper dbHelper = new DbOpenHelper();
		Connection cnx = dbHelper.getCnx();
	}

}
