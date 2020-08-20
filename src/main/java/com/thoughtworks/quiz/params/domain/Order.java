package com.thoughtworks.quiz.params.domain;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private String unit;

    @NotNull
    private int count;
}
