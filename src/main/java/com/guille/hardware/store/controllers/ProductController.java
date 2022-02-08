package com.guille.hardware.store.controllers;

import com.guille.hardware.store.exceptions.ProductWithNoNameException;
import com.guille.hardware.store.models.Product;
import com.guille.hardware.store.services.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("newProduct")
    @ApiOperation(value = "Creates a new Product.",
            notes = "Name must be informed",
            response = Product.class)
    public ResponseEntity<?> newProduct(@RequestBody Product product) throws ProductWithNoNameException {

        Product newProduct = productService.productRegistration(product);

        if (Objects.isNull(newProduct)) return new ResponseEntity<>("The product is null", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<Set<Product>> getAllProducts() {

        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("productsBySections")
    public ResponseEntity<?> getAllSections() {

        Map<String, Long> productsBySection = productService.getProductsAndSections();

        if (productsBySection.isEmpty()) return new ResponseEntity<>("No products available.", HttpStatus.NO_CONTENT);

        return ResponseEntity.ok().body(productsBySection.toString());
    }

    @GetMapping("totalProductsNumber")
    public ResponseEntity<String> totalProductsNumber() {

        return ResponseEntity.ok().body("The total number of products in the DB is " + productService.totalNumber());
    }

    @GetMapping("getProduct/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) {

        Product Product = productService.get(id);

        if (Objects.isNull(Product)) return new ResponseEntity<>("The product doesn't exists", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(Product, HttpStatus.OK);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id, @RequestBody Product Product) {

        Product updatedProduct = productService.updateProduct(id, Product);

        if (Objects.isNull(updatedProduct))
            return new ResponseEntity<>("The product's id " + id + " doesn't exists", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {

        Product deletedProduct = productService.delete(id);

        if (Objects.isNull(deletedProduct)) return new ResponseEntity<>("El usuario no existe", HttpStatus.BAD_REQUEST);

        log.info("Product " + deletedProduct.getName() + " deleted.");
        return new ResponseEntity<>("Product " + deletedProduct.getName() + " corectly deleted.", HttpStatus.OK);
    }

    @DeleteMapping("deleteAll")
    public String deleteAllProducts() {

        productService.deleteAll();

        log.info("All products have been correctly deleted");
        return "All products have been correctly deleted";
    }
}
