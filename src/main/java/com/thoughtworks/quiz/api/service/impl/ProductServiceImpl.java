package com.thoughtworks.quiz.api.service.impl;

import com.thoughtworks.quiz.api.repository.ProductRepository;
import com.thoughtworks.quiz.api.service.ProductService;
import com.thoughtworks.quiz.params.domain.Product;
import com.thoughtworks.quiz.params.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findProductsAll() {
        List<Product> productList = productRepository.findAll().stream()
                .map(item ->
                        Product.builder()
                                .id(item.getId())
                                .name(item.getName())
                                .price(item.getPrice())
                                .image(item.getImage())
                                .unit(item.getUnit())
                                .build())
                .collect(Collectors.toList());
        if (productList.isEmpty()) {
            return null;
        }
        return productList;
    }
}
