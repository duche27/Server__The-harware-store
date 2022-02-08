package com.guille.hardware.store.repositories;

import com.guille.hardware.store.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryUnderTest;

    Product product = new Product("FORNITURE", "desk", 59.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product product2 = new Product("GAMES", "game", 9.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product product3 = new Product("SPORTS", "sport", 9.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product product4 = new Product("SPORTS", "sport2", 9.99, ZonedDateTime.now(), ZonedDateTime.now());

    @Test
    void shouldGetsProductsAndSectionsTest() {
        // given
        productRepositoryUnderTest.save(product);
        productRepositoryUnderTest.save(product2);
        productRepositoryUnderTest.save(product3);
        productRepositoryUnderTest.save(product4);

        // when
        Set<Product> results = productRepositoryUnderTest.getProductsAndSections();

        // then
        assertNotNull(results);
    }
}