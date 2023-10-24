package com.example.ideas.util_Entities.category.controller;

import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.category.repository.CategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void itShouldGetAllCategoriesFromDB() throws Exception {
        //given
        List<Category> expected = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryId"));
        //when
        MvcResult getCategoryResult = mockMvc.perform(get("/categories/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = getCategoryResult
                .getResponse()
                .getContentAsString();

        List<Category> result = objectMapper.readValue(
                contentAsString,
                new TypeReference<>() {
                }
        );

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void itShouldGetCategoryWithId1() throws Exception {
        //given
        Category expected = categoryRepository.findById(1L).orElse(null);
        //when
        MvcResult getCategoryResult = mockMvc.perform(get("/categories/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = getCategoryResult
                .getResponse()
                .getContentAsString();
        Category result = objectMapper.readValue(contentAsString, new TypeReference<>() {});
        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void itShouldNotGetCategoryWithId100() throws Exception {
        //given
        int expected = 404;
        //when
        MvcResult getCategoryResult = mockMvc.perform(get("/categories/id/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        int resultResponseStatus = getCategoryResult
                .getResponse()
                .getStatus();
        //then
        Assertions.assertThat(resultResponseStatus).isEqualTo(expected);
    }

    @Test
    @Disabled
    void getCategoryByName() {
    }
}