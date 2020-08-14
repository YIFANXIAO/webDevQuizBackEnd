package com.thoughtworks.quiz.api.controller;

import com.thoughtworks.quiz.api.service.ProductService;
import com.thoughtworks.quiz.params.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductContorller {

    @Autowired
    ProductService productService;


    @GetMapping("/product/all")
    public ResponseEntity findProductsAll() {
        List<Product> productsAll = productService.findProductsAll();
        if (productsAll == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productsAll);
    }

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

}
