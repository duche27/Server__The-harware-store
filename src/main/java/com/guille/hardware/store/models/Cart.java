package com.guille.hardware.store.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_id_sequence"
    )
    private int cart_id;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    private Set<Product> products = new HashSet<>();
    private double price;
    private ZonedDateTime created_date;
    private ZonedDateTime updated_date;
}

