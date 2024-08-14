package org.ecommerce.productcatalogservice.controllers;

import org.ecommerce.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    // Get List of all the products
    @GetMapping("/products")
    public List<Product> getProducts() {
        return null;
    }

    // Get Product by ID
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return null;
    }

    // Create Product
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

}
