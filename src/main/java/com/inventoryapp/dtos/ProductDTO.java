package com.inventoryapp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEntry;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateExit;
    private Long categoryId; // Solo el ID, no el objeto completo
    private Long warehouseId;
    private Boolean available;
}