package com.example.test_mspr_produit.controllers;

import com.example.test_mspr_produit.db.DbOpenHelper;
import com.example.test_mspr_produit.models.Client;
import com.example.test_mspr_produit.models.Product;
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
@RequestMapping("/products")		// Prefix Path by /products/....
public class ProductController {

    public static final String PRODUCT_MODEL = "current_product";
    public static final String PRODUCTS_MODEL = "products";

    //private Product currentUser;
    private ArrayList<Product> products = new ArrayList<>();

    public ProductController() {

    }

    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {
        try {
            DbOpenHelper test = new DbOpenHelper();
            this.products = test.show_all_product();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute(PRODUCTS_MODEL, products);

        return "products/product_list";
    }


    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /products/120/show
    //@RequestMapping(method = RequestMethod.GET, path = CommonConstant.ROUTE_SHOW)	// Base of @GetMapping(...)
    public String viewProductSheet(Model model, @PathVariable("id") long id_product) {
        Product productFinded = this.findProductById(id_product);

        if (productFinded != null) {
            model.addAttribute(PRODUCT_MODEL, productFinded);
        }

        return "products/fiche_produit";
    }

    @GetMapping(CommonConstant.ROUTE_DELETE)
    public String delClient(Model model, @PathVariable("id") long id_product) {
        Product productFinded = this.findProductById(id_product);

        model.addAttribute(PRODUCT_MODEL, productFinded);
        DbOpenHelper DbHelper = new DbOpenHelper();
        DbHelper.del_product(productFinded);
        return "redirect:/products/";
    }

    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editProduct(Model model, @PathVariable("id") long id_product) {
        Product productFinded = this.findProductById(id_product);

        model.addAttribute(PRODUCT_MODEL, productFinded);
        return "products/form_product";
    }

    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveProduct(Model model, @ModelAttribute Product productSubmit) {
        Product productFinded = this.findProductById(productSubmit.getId_product());

        if (productFinded != null) {
            productFinded.setName(productFinded.getName());
            productFinded.setAvailability(productFinded.getAvailability());
            productFinded.setPrice(productFinded.getPrice());
            productFinded.setStock(productFinded.getStock());
        }
        DbOpenHelper DbHelper = new DbOpenHelper();
        DbHelper.update_product(productSubmit);
        return "redirect:/products/" + productFinded.getId_product() + "/show";
    }


    private Product findProductById(long id) {
        Product productFinded = null;
        // Foreach
        for (Product product : this.products) {
            if (product.getId_product() == id) {
                productFinded = product;
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

        return productFinded;
    }
}



