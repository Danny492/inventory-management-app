package com.inventoryapp.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String code;
    private String description;
    private Double price;
    private int quantity;
    private String name;
    private Long categoryId; // Solo el ID, no el objeto completo
    private Boolean available;
}