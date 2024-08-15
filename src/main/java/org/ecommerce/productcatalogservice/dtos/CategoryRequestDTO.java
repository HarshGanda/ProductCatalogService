package org.ecommerce.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    private Long id;
    private String name;
    private String description;
}
