package com.thoughtworks.quiz.params.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "product_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private int productId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private int price;

    @Setter
    @Getter
    private String unit;

    @Setter
    @Getter
    private int count;

}
