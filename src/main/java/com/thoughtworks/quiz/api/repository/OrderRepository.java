package com.thoughtworks.quiz.api.repository;

import com.thoughtworks.quiz.params.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<OrderDto, Integer> {

    Page<OrderDto> findAll(Pageable pageable);

    @Override
    List<OrderDto> findAll();
}
