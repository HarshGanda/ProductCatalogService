package org.ecommerce.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private CategoryDTO category;
}
