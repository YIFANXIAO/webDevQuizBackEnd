package com.thoughtworks.quiz;

import com.thoughtworks.quiz.api.repository.OrderRepository;
import com.thoughtworks.quiz.api.repository.ProductRepository;
import com.thoughtworks.quiz.api.service.OrderService;
import com.thoughtworks.quiz.common.errors.ErrorCode;
import com.thoughtworks.quiz.params.dto.OrderDto;
import com.thoughtworks.quiz.params.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        orderRepository.deleteAll();

        ProductDto product1 = ProductDto.builder().name("可乐").price(5).unit("罐").image("https://img14.360buyimg.com/n0/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg").build();
        ProductDto product2 = ProductDto.builder().name("雪碧").price(5).unit("罐").image("https://p1.ssl.qhimg.com/dr/270_500_/t01c9088d8be1e33b20.jpg?size=268x201").build();
        ProductDto product3 = ProductDto.builder().id(10).name("柠檬").price(5).unit("个").image("http://images.meishij.net/p/20110831/7b3b546acb130eaacc2fc7e44ed09f3d_180x180.jpg").build();
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        List<ProductDto> all = productRepository.findAll();

        orderService.addOrderByProductId(all.get(0).getId());
        orderService.addOrderByProductId(all.get(0).getId());
        orderService.addOrderByProductId(all.get(1).getId());
    }

    @Test
    void should_get_all_order_pageable_given_none() throws Exception {
        mockMvc.perform(get("/order/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_delete_order_given_id() throws Exception {

        OrderDto orderDto = orderRepository.findAll().get(0);

        mockMvc.perform(delete("/order/" + orderDto.getId())
                .param("orderId", "" + orderDto.getId())
        );

        assertEquals(1, orderRepository.findAll().size());
    }

    @Test
    void should_add_order_given_product_id() throws Exception {
        List<ProductDto> allProducts = productRepository.findAll();

        mockMvc.perform(post("/order/" + allProducts.get(0).getId()))
                .andExpect(status().isOk());

        List<OrderDto> allOrders = orderRepository.findAll();
        assertEquals(allOrders.get(0).getCount(), 3);
    }

    @Test
    void should_throw_NO_DATA_EXPECTION_given_none() throws Exception {
        orderRepository.deleteAll();

        mockMvc.perform(get("/order/all"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ErrorCode.ORDER_NO_Data_Error.getCode())))
                .andExpect(jsonPath("$.message", is(ErrorCode.ORDER_NO_Data_Error.getMessage())));
    }

    @Test
    void should_throw_Illegal_params_exception_given_wrong_id() throws Exception {
        mockMvc.perform(delete("/order/" + 999999999))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ErrorCode.DELETE_ORDER_PARAM_ERROR.getCode())))
                .andExpect(jsonPath("$.message", is(ErrorCode.DELETE_ORDER_PARAM_ERROR.getMessage())));
    }

}
