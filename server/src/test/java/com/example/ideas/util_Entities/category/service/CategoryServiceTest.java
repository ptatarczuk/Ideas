package com.example.ideas.util_Entities.category.service;

import com.example.ideas.util_Entities.category.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock private CategoryRepository categoryRepository;

    @InjectMocks private CategoryService underTest;

    @Test
    void canGetAllCategories() {
        //given
        // when
        underTest.getCategories();
        // then
        verify(categoryRepository).findAll(Sort.by(Sort.Direction.ASC, "categoryId"));
    }

    @Test
    void canGetCategoryById() {
        //given
        // when
        underTest.getCategoryById(1L);
        //then
        verify(categoryRepository).findById(1L);
    }

    @Test
    void canGetCategoryByName() {
        //given
        // when
        underTest.getCategoryByName("Branding");
        //then
        verify(categoryRepository).findCategoryByCategoryName("Branding");
    }

}