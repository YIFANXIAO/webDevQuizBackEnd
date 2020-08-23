package com.thoughtworks.quiz.api.controller;

import com.thoughtworks.quiz.api.service.OrderService;
import com.thoughtworks.quiz.common.exception.IllegalParamsException;
import com.thoughtworks.quiz.common.exception.NoDataException;
import com.thoughtworks.quiz.params.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/all")
    public ResponseEntity findOrdersAll() throws NoDataException {
        return ResponseEntity.ok(orderService.getOrdersByPage());
    }


    @DeleteMapping("/order/{orderId}")
    public ResponseEntity deleteOrderById(@PathVariable int orderId) throws IllegalParamsException {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/order/{productId}")
    public ResponseEntity addOrderByProductId(@PathVariable String productId) {
        orderService.addOrderByProductId(Integer.valueOf(productId));
        return ResponseEntity.ok().build();
    }
}
