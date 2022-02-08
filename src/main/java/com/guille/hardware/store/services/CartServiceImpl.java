package com.guille.hardware.store.services;

import com.guille.hardware.store.commons.GenericServiceImpl;
import com.guille.hardware.store.exceptions.EmptyCartException;
import com.guille.hardware.store.models.Cart;
import com.guille.hardware.store.models.Product;
import com.guille.hardware.store.repositories.CartRepository;
import com.guille.hardware.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Objects;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl extends GenericServiceImpl<Cart, Integer> implements CartService {

    @Autowired
    private CartRepository cartRepository;

    CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CrudRepository<Cart, Integer> getDao() {
        return cartRepository;
    }

    public Cart cartPurchase(Cart cart) throws EmptyCartException {

        if (cart.getProducts().isEmpty()) throw new EmptyCartException("The new purchased cart must have products");
        cart.setPrice(cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
        cart.setCreated_date(ZonedDateTime.now());
        cart.setUpdated_date(ZonedDateTime.now());

        save(cart);

        log.info("Cart with id " + cart.getCart_id() + " added.");
        return cart;
    }

    @Override
    public Cart updateCart(Integer id, Cart cart) {

        Cart updatedCart = get(id);

        if (Objects.isNull(updatedCart)) return null;

        if (!cart.getProducts().isEmpty()) updatedCart.setProducts(cart.getProducts());
        updatedCart.setPrice(cart.getProducts().stream().mapToDouble(Product::getPrice).sum());
        updatedCart.setUpdated_date(ZonedDateTime.now());

        log.info("Cart '" + updatedCart.getCart_id() + "' correctly modified.");

        return updatedCart;
    }
}