package com.inventoryapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDTO {
    private Long id;
    private String type;
    private int quantity;
    private Date date;
    private Long userId;
    private Long productId;
}
