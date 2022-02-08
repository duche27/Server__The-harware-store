package com.guille.hardware.store.repositories;

import com.guille.hardware.store.models.Cart;
import com.guille.hardware.store.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends CrudRepository<Cart, Integer> {
}
