package org.ecommerce.productcatalogservice.services;

import org.ecommerce.productcatalogservice.clients.FakeStoreApiClient;
import org.ecommerce.productcatalogservice.dtos.FakeStoreProductRequestDTO;
import org.ecommerce.productcatalogservice.dtos.FakeStoreProductResponseDTO;
import org.ecommerce.productcatalogservice.models.Category;
import org.ecommerce.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.ecommerce.productcatalogservice.constants.Constants.FAKE_STORE_API;

@Service("fps")
public class FakeStoreProductService implements IProductService {

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    // Get Product by ID
    @Override
    public Product getProductById(Long id) {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreApiClient.getProductById(id);
        if(fakeStoreProductResponseDTO != null) {
            return getProduct(fakeStoreProductResponseDTO);
        }
        return null;
    }

    // Create Product
    @Override
    public Product createProduct(Product product) {
        FakeStoreProductRequestDTO input = getFakeStoreProductDto(product);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreApiClient.createProduct(input);
        if(fakeStoreProductResponseDTO != null) {
            return getProduct(fakeStoreProductResponseDTO);
        }
        return null;
    }

    // Get List of all the products
    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDTO[] response = fakeStoreApiClient.getProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDTO fakeStoreProductResponseDTO : response) {
            products.add(getProduct(fakeStoreProductResponseDTO));
        }
        return products;
    }

    // Replace Product
    @Override
    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductRequestDTO input = getFakeStoreProductDto(product);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreApiClient.replaceProduct(id, input);
        if (fakeStoreProductResponseDTO != null) {
            return getProduct(fakeStoreProductResponseDTO);
        }
        return null;
    }

    // Delete Product
    @Override
    public Product deleteProduct(Long id) {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreApiClient.deleteProduct(id);
        if (fakeStoreProductResponseDTO != null) {
            return getProduct(fakeStoreProductResponseDTO);
        }
        return null;
    }

    // Convert Product to FakeStoreProductRequestDTO
    private FakeStoreProductRequestDTO getFakeStoreProductDto(Product product) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle(product.getName());
        fakeStoreProductRequestDTO.setPrice(product.getPrice());
        fakeStoreProductRequestDTO.setDescription(product.getDescription());
        fakeStoreProductRequestDTO.setImage(product.getImageUrl());
        fakeStoreProductRequestDTO.setCategory(product.getCategory().getName());
        return fakeStoreProductRequestDTO;
    }

    // Convert FakeStoreProductResponseDTO to Product
    private Product getProduct(FakeStoreProductResponseDTO fakeStoreProductResponseDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductResponseDTO.getId());
        product.setName(fakeStoreProductResponseDTO.getTitle());
        product.setPrice(fakeStoreProductResponseDTO.getPrice());
        product.setDescription(fakeStoreProductResponseDTO.getDescription());
        product.setImageUrl(fakeStoreProductResponseDTO.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductResponseDTO.getCategory());
        product.setCategory(category);
        return product;
    }

}
