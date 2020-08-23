package com.thoughtworks.quiz.api.service.impl;

import com.thoughtworks.quiz.api.repository.OrderRepository;
import com.thoughtworks.quiz.api.repository.ProductRepository;
import com.thoughtworks.quiz.api.service.OrderService;
import com.thoughtworks.quiz.common.errors.ErrorCode;
import com.thoughtworks.quiz.common.exception.IllegalParamsException;
import com.thoughtworks.quiz.common.exception.NoDataException;
import com.thoughtworks.quiz.params.domain.Order;
import com.thoughtworks.quiz.params.dto.OrderDto;
import com.thoughtworks.quiz.params.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Order> getOrdersByPage() throws NoDataException {
        List<Order> collect = orderRepository.findAll().stream().map(
                item -> Order.builder().id(item.getId()).name(item.getName())
                        .count(item.getCount()).price(item.getPrice())
                        .unit(item.getUnit()).build()
        ).collect(Collectors.toList());

        if (collect.isEmpty()) {
            throw new NoDataException(ErrorCode.ORDER_NO_Data_Error);
        }
        return collect;
    }

    @Override
    public void deleteOrderById(int orderId) throws IllegalParamsException {
        try {
            orderRepository.findById(orderId).get();
        } catch (Exception e) {
            throw new IllegalParamsException(ErrorCode.DELETE_ORDER_PARAM_ERROR);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public void addOrderByProductId(int productId) throws IllegalParamsException {
        List<OrderDto> isOrderDtoExist = orderRepository.findByProductId(productId);
        ProductDto productDto = null;
        OrderDto orderDto;

        try {
            productDto = productRepository.findById(productId).get();
        } catch (Exception e) {
            throw new IllegalParamsException(ErrorCode.SELECT_PRODUCT_PARAM_ERROR);
        }

        if (!isOrderDtoExist.isEmpty()) {
            orderDto = isOrderDtoExist.get(0);
            orderDto.setCount(orderDto.getCount() + 1);
            orderDto.setPrice(productDto.getPrice() * orderDto.getCount());
        }else {
            orderDto = OrderDto.builder().name(productDto.getName()).price(productDto.getPrice())
                    .unit(productDto.getUnit()).productId(productId).count(1).build();
        }
        orderRepository.save(orderDto);
    }


}
