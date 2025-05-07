package com.inventoryapp.dtos;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    @Column(unique = true)
    private String code;
    private String description;
    private Double price;
    private Double costPrice;
    private int stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @Column(unique = true)
    private String name;
    private Long categoryId; // Solo el ID, no el objeto completo
    private Long warehouseId;
    private Boolean available;
}


