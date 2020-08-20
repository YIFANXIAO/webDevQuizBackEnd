package com.thoughtworks.quiz.api.service;

import com.thoughtworks.quiz.params.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByPage();

    void deleteOrderById(int orderId);

    void addOrderByProductId(int productId);
}
