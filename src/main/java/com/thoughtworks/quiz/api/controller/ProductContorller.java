package com.thoughtworks.quiz.api.controller;

import com.thoughtworks.quiz.api.service.ProductService;
import com.thoughtworks.quiz.common.exception.IllegalParamsException;
import com.thoughtworks.quiz.params.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductContorller {

    @Autowired
    ProductService productService;


    @GetMapping("/products")
    public ResponseEntity findProductsAll() {
        return ResponseEntity.ok(productService.findProductsAll());
    }

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody Product product) throws IllegalParamsException {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

}
