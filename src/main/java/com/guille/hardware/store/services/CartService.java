package com.guille.hardware.store.services;

import com.guille.hardware.store.commons.GenericService;
import com.guille.hardware.store.exceptions.EmptyCartException;
import com.guille.hardware.store.models.Cart;

public interface CartService extends GenericService<Cart, Integer> {

    Cart updateCart(Integer id, Cart cart);

    Cart cartPurchase(Cart cart) throws EmptyCartException;
}
