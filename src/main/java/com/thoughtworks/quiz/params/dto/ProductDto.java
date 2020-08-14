package com.thoughtworks.quiz.params.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private int id;

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
    private String image;

}
