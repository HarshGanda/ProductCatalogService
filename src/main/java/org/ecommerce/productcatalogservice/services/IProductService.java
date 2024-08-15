package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product replaceProduct(Product product, Long id);
    Product deleteProduct(Long id);
}
