package com.thoughtworks.quiz.api.repository;


import com.thoughtworks.quiz.params.dto.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<ProductDto, Integer> {

    @Override
    List<ProductDto> findAll();

    List<ProductDto> findByName(String name);

}
