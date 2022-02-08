package com.guille.hardware.store.controllers;

import com.guille.hardware.store.exceptions.EmptyCartException;
import com.guille.hardware.store.models.Cart;
import com.guille.hardware.store.services.CartService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.guille.hardware.store.commons.PropertiesLoader.app_values;

@RestController
@RequestMapping("api/carts")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("newCart")
    @ApiOperation(value = "Purchase a new cart.",
            notes = "All items must exist",
            response = Cart.class)
    public ResponseEntity<?> newCart(@RequestBody Cart cart) throws EmptyCartException {

        Cart newCart = cartService.cartPurchase(cart);

        if (Objects.isNull(newCart)) return new ResponseEntity<>("The cart is null", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(newCart, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<Set<Cart>> getAllCarts() {

        return ResponseEntity.ok().body(cartService.getAll());
    }

    @GetMapping("totalCartsNumber")
    public ResponseEntity<String> totalCartsNumber() {

        return ResponseEntity.ok().body("The total number of carts in the DB is " + cartService.totalNumber());
    }

    @GetMapping("getCart/{id}")
    public ResponseEntity<?> getCart(@PathVariable("id") Integer id) {

        Cart Cart = cartService.get(id);

        if (Objects.isNull(Cart)) return new ResponseEntity<>("The cart doesn't exists", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(Cart, HttpStatus.OK);
    }

    @PutMapping("updateCart/{id}")
    public ResponseEntity<?> updateCart(@PathVariable("id") Integer id, @RequestBody Cart Cart) {

        Cart updatedCart = cartService.updateCart(id, Cart);

        if (Objects.isNull(updatedCart))
            return new ResponseEntity<>("The cart's id " + id + " doesn't exists", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("deleteCart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Integer id) {

        Cart deletedCart = cartService.delete(id);

        if (Objects.isNull(deletedCart)) return new ResponseEntity<>("The cart doesn't exist", HttpStatus.BAD_REQUEST);

        log.info("Cart " + deletedCart.getCart_id() + " deleted.");
        return new ResponseEntity<>("Cart " + deletedCart.getCart_id() + " corectly deleted.", HttpStatus.OK);
    }

    @DeleteMapping("deleteAll")
    public String deleteAllCarts() {

        cartService.deleteAll();

        log.info("All carts have been correctly deleted");
        return "All carts have been correctly deleted";
    }
}
