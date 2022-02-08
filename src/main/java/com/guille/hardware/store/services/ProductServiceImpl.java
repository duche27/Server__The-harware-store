package com.guille.hardware.store.services;

import com.guille.hardware.store.commons.GenericServiceImpl;
import com.guille.hardware.store.exceptions.ProductWithNoNameException;
import com.guille.hardware.store.models.Sections;
import com.guille.hardware.store.models.Product;
import com.guille.hardware.store.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.guille.hardware.store.commons.Utils.*;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl extends GenericServiceImpl<Product, Integer> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CrudRepository<Product, Integer> getDao() {
        return productRepository;
    }

    public Product productRegistration(Product product) throws ProductWithNoNameException {

        if (isNullOrBlank(product.getName())) throw new ProductWithNoNameException("The new product must have a name");
        if (product.getPrice() == null) product.setPrice((double) 0);
        if (isNullOrNotInEnum(Sections.class, product.getSection())) product.setSection("VARIOUS");
        product.setCreated_date(ZonedDateTime.now());
        product.setUpdated_date(ZonedDateTime.now());

        save(product);

        log.info("Article with id " + product.getProduct_id() + " added.");
        return product;
    }

    public Map<String, Long> getProductsAndSections() {

        Set<Product> allProducts = productRepository.getProductsAndSections();
        Map<String, Long> allProductsBySections = new HashMap<>();

        if (!allProducts.isEmpty()) allProductsBySections = allProducts.stream()
                .collect(Collectors.groupingBy(Product::getSection, Collectors.counting()));

        return allProductsBySections;
    }

    @Override
    public Product updateProduct(Integer id, Product product) {

        Product updatedProduct = get(id);

        if (Objects.isNull(updatedProduct)) return null;

        if (!isNullOrBlank(product.getName())) updatedProduct.setName(product.getName());
        if (product.getPrice() != null) updatedProduct.setPrice(product.getPrice());
        if (!isNullOrNotInEnum(Sections.class, product.getSection()))
            updatedProduct.setSection(product.getSection());
        updatedProduct.setUpdated_date(ZonedDateTime.now());

        log.info("Product '" + updatedProduct.getName() + "' correctly modified.");

        return updatedProduct;
    }
}