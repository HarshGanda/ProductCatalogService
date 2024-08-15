package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.dtos.*;
import org.ecommerce.productcatalogservice.models.Category;
import org.ecommerce.productcatalogservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sps")
public class StorageProductService implements IProductService {

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    // Convert Product to ProductRequestDTO
    private ProductRequestDTO getProductDto(Product product) {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setName(product.getName());
        productRequestDTO.setPrice(product.getPrice());
        productRequestDTO.setDescription(product.getDescription());
        productRequestDTO.setImageUrl(product.getImageUrl());
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
        categoryRequestDTO.setName(product.getCategory().getName());
        categoryRequestDTO.setDescription(product.getCategory().getDescription());
        productRequestDTO.setCategory(categoryRequestDTO);
        return productRequestDTO;
    }

    // Convert ProductResponseDTO to Product
    private Product getProduct(ProductResponseDTO productResponseDTO) {
        Product product = new Product();
        product.setId(productResponseDTO.getId());
        product.setName(productResponseDTO.getName());
        product.setPrice(productResponseDTO.getPrice());
        product.setDescription(productResponseDTO.getDescription());
        product.setImageUrl(productResponseDTO.getImageUrl());
        Category category = new Category();
        category.setId(productResponseDTO.getCategory().getId());
        category.setName(productResponseDTO.getCategory().getName());
        category.setDescription(productResponseDTO.getCategory().getDescription());
        product.setCategory(category);
        return product;
    }
}
