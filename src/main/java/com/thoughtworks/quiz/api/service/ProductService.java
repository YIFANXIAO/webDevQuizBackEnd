package com.thoughtworks.quiz.api.service;

import com.thoughtworks.quiz.params.domain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findProductsAll();

    void addProduct(Product product);
}
