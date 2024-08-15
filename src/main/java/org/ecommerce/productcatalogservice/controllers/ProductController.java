package org.ecommerce.productcatalogservice.controllers;

import org.ecommerce.productcatalogservice.dtos.CategoryResponseDTO;
import org.ecommerce.productcatalogservice.dtos.ProductRequestDTO;
import org.ecommerce.productcatalogservice.dtos.ProductResponseDTO;
import org.ecommerce.productcatalogservice.models.Category;
import org.ecommerce.productcatalogservice.models.Product;
import org.ecommerce.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("fps")
    private IProductService productService;

    // Get List of all the products API
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productResponseDTO = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            productResponseDTO.add(getProductResponseDTO(product));
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("called by", "Harsh Ganda");
        headers.add("total products", String.valueOf(productResponseDTO.size()));
        return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
    }

    // Get Product by ID API
    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid ID");
            }
            Product product = productService.getProductById(id);
            ProductResponseDTO productResponseDTO = getProductResponseDTO(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "Harsh Ganda");
            headers.add("product id", String.valueOf(id));
            return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
        }
        catch (IllegalArgumentException exception) {
            throw exception;
        }
    }

    // Create Product API
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product productRequest = getProduct(productRequestDTO);
        Product product = productService.createProduct(productRequest);
        ProductResponseDTO productResponseDTO = getProductResponseDTO(product);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("called by", "Harsh Ganda");
        headers.add("created", "true");
        return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
    }

    // Replace Product API
    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDTO> replaceProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable("id") Long id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid ID");
            }
            Product productRequest = getProduct(productRequestDTO);
            Product product = productService.replaceProduct(productRequest, id);
            ProductResponseDTO productResponseDTO = getProductResponseDTO(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "Harsh Ganda");
            headers.add("replaced", "true");
            return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
        }
        catch (IllegalArgumentException exception) {
            throw exception;
        }
    }

    // Delete Product API
    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") Long id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Invalid ID");
            }
            Product response = productService.deleteProduct(id);
            ProductResponseDTO productResponseDTO = getProductResponseDTO(response);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "Harsh Ganda");
            headers.add("deleted", "true");
            return new ResponseEntity<>(productResponseDTO, headers, HttpStatus.OK);
        }
        catch (IllegalArgumentException exception) {
            throw exception;
        }
    }

    // Convert Product to ProductResponseDTO
    private ProductResponseDTO getProductResponseDTO(Product product) {
        if(product == null) {
            return null;
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setImageUrl(product.getImageUrl());
        productResponseDTO.setDescription(product.getDescription());
        if(product.getCategory() != null) {
            CategoryResponseDTO category = new CategoryResponseDTO();
            category.setId(product.getCategory().getId());
            category.setName(product.getCategory().getName());
            productResponseDTO.setCategory(category);
        }
        return productResponseDTO;
    }

    // Convert ProductRequestDTO to Product
    private Product getProduct(ProductRequestDTO productRequestDTO) {
        if(productRequestDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setImageUrl(productRequestDTO.getImageUrl());
        product.setDescription(productRequestDTO.getDescription());
        if (productRequestDTO.getCategory() != null) {
            Category category = new Category();
            category.setName(productRequestDTO.getCategory().getName());
            category.setDescription(productRequestDTO.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }
}
