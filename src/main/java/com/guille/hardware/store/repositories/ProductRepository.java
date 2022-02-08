package com.guille.hardware.store.repositories;

import com.guille.hardware.store.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products", nativeQuery = true)
    Set<Product> getProductsAndSections();
}
