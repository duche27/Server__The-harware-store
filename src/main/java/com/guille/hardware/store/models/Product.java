package com.guille.hardware.store.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    private int product_id;
    private String section;
    private String name;
    private Double price;
    private ZonedDateTime created_date;
    private ZonedDateTime updated_date;

    public Product(String section, String name, Double price, ZonedDateTime created_date, ZonedDateTime updated_date) {
        this.section = section;
        this.name = name;
        this.price = price;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }
}

