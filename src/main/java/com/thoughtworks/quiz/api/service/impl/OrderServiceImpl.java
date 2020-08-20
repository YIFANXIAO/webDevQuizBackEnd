package com.thoughtworks.quiz.api.service.impl;

import com.thoughtworks.quiz.api.repository.OrderRepository;
import com.thoughtworks.quiz.api.repository.ProductRepository;
import com.thoughtworks.quiz.api.service.OrderService;
import com.thoughtworks.quiz.params.domain.Order;
import com.thoughtworks.quiz.params.dto.OrderDto;
import com.thoughtworks.quiz.params.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

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

    @Override
    public void addOrderByProductId(int productId) {
        List<OrderDto> isOrderDtoExist = orderRepository.findByProductId(productId);

        ProductDto productDto = productRepository.findById(productId).get();
        OrderDto orderDto;

        if (!isOrderDtoExist.isEmpty()) {
            orderDto = isOrderDtoExist.get(0);
            orderDto.setCount(orderDto.getCount() + 1);
            orderDto.setPrice(productDto.getPrice() * orderDto.getCount());
        }else {
            orderDto = OrderDto.builder()
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .unit(productDto.getUnit())
                    .productId(productId)
                    .count(1)
                    .build();
        }
        orderRepository.save(orderDto);
    }


}
