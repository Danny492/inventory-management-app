package com.inventoryapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    private Double price;

    private int quantity;

    private String name;

    private Date dateEntry;

    private Date dateExit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_warehouse", joinColumns = @JoinColumn(name = "product"), inverseJoinColumns = @JoinColumn(name = "warehouse"))
    private Set<Warehouse> warehouses;

    private Boolean available;

}
