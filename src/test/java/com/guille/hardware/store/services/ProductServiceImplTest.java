package com.guille.hardware.store.services;

import com.guille.hardware.store.exceptions.ProductWithNoNameException;
import com.guille.hardware.store.models.Product;
import com.guille.hardware.store.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductServiceImpl(productRepository);
    }

    Product dbProduct = new Product(1, "FORNITURE", "desk", 59.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product dbProduct2 = new Product(2, "GAMES", "game", 9.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product dbProduct3 = new Product(3, "SPORTS", "sport", 9.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product dbProduct4 = new Product(4, "SPORTS", "sport2", 9.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product newSectionProduct = new Product("SPORTS", null, null, null, null);
    Product newNameProduct = new Product(null, "ball", null, null, null);
    Product newPriceProduct = new Product(null, null, 89.99, null, null);
    Product productWithNoName = new Product("FORNITURE", null, 59.99, ZonedDateTime.now(), ZonedDateTime.now());
    Product productWithNoPriceAndNoSection = new Product(null, "chair", null, ZonedDateTime.now(), ZonedDateTime.now());

    @Test
    @Disabled
    void getDao() {
    }

    @Test
    @DisplayName("Test passes if the product gets registered in the DB")
    void completeAndNoNameProductsRegistrationTest() throws ProductWithNoNameException {

        // when product is complete
        productServiceUnderTest.productRegistration(dbProduct);

        // then registered product should be complete
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(dbProduct);

        // when product has no name --> should generates an exception
        assertThatThrownBy(() -> {
            productServiceUnderTest.productRegistration(productWithNoName);
        }).isInstanceOf(ProductWithNoNameException.class)
                .hasMessageContaining("The new product must have a name");
    }

    @Test
    @DisplayName("Test passes if the product gets in the DB with price 0 and section VARIOUS")
    void noPriceAndNoSectionProductRegistrationTest() throws ProductWithNoNameException {

        // when product has no price and no section
        productServiceUnderTest.productRegistration(productWithNoPriceAndNoSection);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        // then registered product should has price = 0 and section = VARIOUS
        assertThat(capturedProduct.getPrice()).isEqualTo(0);
        assertThat(capturedProduct.getSection()).isEqualTo("VARIOUS");
    }

    @Test
    void getProductsAndSectionsTest() throws ProductWithNoNameException {

        // given
        when(productRepository.getProductsAndSections()).thenReturn(Set.of(dbProduct, dbProduct2, dbProduct3, dbProduct4));

        // when
        Map<String, Long> result = productServiceUnderTest.getProductsAndSections();
        int expectedTotalSections = 3;
        int expectedFornitureItems = 1;
        int expectedSportsItems = 2;
        int expectedGamesItems = 1;

        // then
        assertThat(result.size()).isEqualTo(expectedTotalSections);
        assertThat(result.get("FORNITURE")).isEqualTo(expectedFornitureItems);
        assertThat(result.get("SPORTS")).isEqualTo(expectedSportsItems);
        assertThat(result.get("GAMES")).isEqualTo(expectedGamesItems);
    }

    @Test
    void updateProduct() throws ProductWithNoNameException {

        // given
        int productID = 1;

        // when
        when(productRepository.findById(productID)).thenReturn(Optional.of(dbProduct));
        Product sectionUpdatedResult = productServiceUnderTest.updateProduct(productID, newSectionProduct);
        Product nameUpdatedResult = productServiceUnderTest.updateProduct(productID, newNameProduct);
        Product priceUpdatedResult = productServiceUnderTest.updateProduct(productID, newPriceProduct);

        // then
        assertThat(newSectionProduct.getSection()).isEqualTo(sectionUpdatedResult.getSection());
        assertThat(newNameProduct.getName()).isEqualTo(nameUpdatedResult.getName());
        assertThat(newPriceProduct.getPrice()).isEqualTo(priceUpdatedResult.getPrice());

    }
}