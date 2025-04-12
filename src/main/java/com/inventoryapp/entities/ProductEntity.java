package com.inventoryapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductEntity {
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
    private CategoryEntity category;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Supplier supplier;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private LocationStock locationStock;
//

    private Boolean available;

}
