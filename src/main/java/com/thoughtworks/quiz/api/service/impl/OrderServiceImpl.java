package com.thoughtworks.quiz.api.service.impl;

import com.thoughtworks.quiz.api.repository.OrderRepository;
import com.thoughtworks.quiz.api.service.OrderService;
import com.thoughtworks.quiz.params.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByPage() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Order> collect = orderRepository.findAll(pageable).get().map(
                item -> Order.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .count(item.getCount())
                        .price(item.getPrice())
                        .unit(item.getUnit())
                        .build()

        ).collect(Collectors.toList());

        return collect;
    }

    @Override
    public void deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
    }


}
