package org.ecommerce.productcatalogservice.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super("Product not found");
    }
}
