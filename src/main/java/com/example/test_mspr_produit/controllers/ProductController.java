package com.example.test_mspr_produit.controllers;

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
@RequestMapping("/products")		// Prefix Path by /products/....
public class ProductController {

    public static final String PRODUCT_MODEL = "current_product";
    public static final String PRODUCTS_MODEL = "products";

    //private Product currentUser;
    private ArrayList<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product()
                .setName("rivet")
                .setQuantity(12)
                .setId(1));
        products.add(new Product().setName("Vis").setId(2));
        products.add(new Product().setName("Boulon").setId(3));
        products.add(new Product().setName("Ecrou").setId(4));
    }

    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {
        model.addAttribute(PRODUCTS_MODEL, products);

        return "products/product_list";
    }


    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /products/120/show
    //@RequestMapping(method = RequestMethod.GET, path = CommonConstant.ROUTE_SHOW)	// Base of @GetMapping(...)
    public String viewProductSheet(Model model, @PathVariable("id") long id) {
        Product userFinded = this.findProductById(id);

        if (userFinded != null) {
            model.addAttribute(PRODUCT_MODEL, userFinded);
        }

        return "products/fiche_produit";
    }


    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editProduct(Model model, @PathVariable("id") long id) {
        Product productFinded = this.findProductById(id);

        model.addAttribute(PRODUCT_MODEL, productFinded);
        return "products/form_product";
    }

    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveProduct(Model model, @ModelAttribute Product productSubmit) {
        Product productFinded = this.findProductById(productSubmit.getId());

        if (productFinded != null) {
            productFinded.setName(productSubmit.getName());
            productFinded.setQuantity(productSubmit.getQuantity());
        }

        return "redirect:/products/" + productFinded.getId() + "/show";
    }


    private Product findProductById(long id) {
        Product productFinded = null;
        // Foreach
        for (Product product : this.products) {
            if (product.getId() == id) {
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



