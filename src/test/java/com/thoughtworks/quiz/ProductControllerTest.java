package com.thoughtworks.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.quiz.api.repository.ProductRepository;
import com.thoughtworks.quiz.params.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){

        productRepository.deleteAll();

        ProductDto product1 = ProductDto.builder().name("可乐").price(5).unit("罐").image("https://img14.360buyimg.com/n0/jfs/t4705/83/2924377281/70031/aed9bbd3/58f5629dN79b4406c.jpg").build();
        ProductDto product2 = ProductDto.builder().name("雪碧").price(5).unit("罐").image("https://p1.ssl.qhimg.com/dr/270_500_/t01c9088d8be1e33b20.jpg?size=268x201").build();

        productRepository.saveAll(Arrays.asList(product1, product2));
    }

    @Test
    public void should_return_all_products_given_none() throws Exception {
        mockMvc.perform(get("/product/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void should_get_badRequest_when_no_product_given_none() throws Exception {
        productRepository.deleteAll();
        mockMvc.perform(get("/product/all"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shuld_add_product_given_product() throws Exception {
        ProductDto product3 = ProductDto.builder().id(10).name("柠檬").price(5).unit("罐").image("http://images.meishij.net/p/20110831/7b3b546acb130eaacc2fc7e44ed09f3d_180x180.jpg").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(product3);
        mockMvc.perform(post("/product").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(productRepository.findAll().size(), 3);
    }
}
