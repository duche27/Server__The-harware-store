package com.guille.hardware.store.services;

import com.guille.hardware.store.commons.GenericService;
import com.guille.hardware.store.exceptions.ProductWithNoNameException;
import com.guille.hardware.store.models.Product;

import java.util.Map;

public interface ProductService extends GenericService<Product, Integer> {

    Product updateProduct(Integer id, Product Product);

    Product productRegistration(Product Product) throws ProductWithNoNameException;

    Map<String, Long> getProductsAndSections();
}
