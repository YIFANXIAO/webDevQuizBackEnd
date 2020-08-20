package com.thoughtworks.quiz;

import com.thoughtworks.quiz.api.repository.OrderRepository;
import com.thoughtworks.quiz.params.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();

        OrderDto order1 = OrderDto.builder().name("可乐").price(5).count(3).unit("罐").build();
        OrderDto order2 = OrderDto.builder().name("雪碧").price(5).count(2).unit("瓶").build();

        orderRepository.saveAll(Arrays.asList(order1, order2));
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

}
