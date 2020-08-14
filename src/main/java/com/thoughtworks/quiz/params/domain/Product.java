package com.thoughtworks.quiz.params.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private String unit;

    @NotNull
    private String image;

}
