package com.thoughtworks.quiz.api.controller;

import com.thoughtworks.quiz.api.service.OrderService;
import com.thoughtworks.quiz.params.domain.Order;
import com.thoughtworks.quiz.params.domain.Product;
import com.thoughtworks.quiz.params.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/all")
    public ResponseEntity findProductsAll() {
        List<Order> ordersByPage = orderService.getOrdersByPage();
        if (ordersByPage == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ordersByPage);
    }


    @DeleteMapping("/order/{orderId}")
    public ResponseEntity deleteOrderById(@PathVariable int orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }
}
