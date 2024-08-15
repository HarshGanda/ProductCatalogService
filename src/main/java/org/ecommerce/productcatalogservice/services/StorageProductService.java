package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.models.Product;
import org.ecommerce.productcatalogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sps")
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get Product by ID
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Create Product
    @Override
    public Product createProduct(Product product) {
        product.setId(generateNextId());
        return productRepository.save(product);
    }

    // Get List of all the products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Update Product
    @Override
    public Product replaceProduct(Product product, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setImageUrl(product.getImageUrl());
                    existingProduct.setCategory(product.getCategory());
                    return productRepository.save(existingProduct);
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productRepository.save(product);
                });
    }

    // Delete Product
    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if( product == null ) {
            return null;
        }
        productRepository.deleteById(id);
        return product;
    }

    // Method to generate the next incremental ID
    private Long generateNextId() {
        return productRepository.findAll().stream()
                .mapToLong(Product::getId)
                .max()
                .orElse(0L) + 1;
    }

}
