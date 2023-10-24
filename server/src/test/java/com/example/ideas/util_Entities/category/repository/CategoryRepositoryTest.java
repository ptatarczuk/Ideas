package com.example.ideas.util_Entities.category.repository;

import com.example.ideas.util_Entities.category.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository underTest;

    @Test
    void itShouldFindCategoryByCategoryNameEqualsBranding() {
            // given
            // when
            Category expected = underTest.findCategoryByCategoryName("Branding").get();

            // then
            assertEquals("Branding", expected.getCategoryName());
    }

    @Test
    void itShouldNotFindCategoryByCategoryNameEqualsTest() {
        //given
        //when
        Optional<Category> categoryOptional = underTest.findCategoryByCategoryName("test");
        boolean actual = categoryOptional.isPresent();

        assertThat(actual).isFalse();
    }
}