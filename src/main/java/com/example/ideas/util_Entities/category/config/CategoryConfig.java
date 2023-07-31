//package com.example.ideas.category.config;
//
//import com.example.ideas.category.model.Category;
//import com.example.ideas.category.repository.CategoryRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.util.List;
//
//@Configuration
//public class CategoryConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerCategory(CategoryRepository repository) {
//        return args -> {
//            Category c1 = new Category(1L, "Physical Product");
//            Category c2 = new Category(2L, "Graphic Design");
//            Category c3 = new Category(3L, "Marketing");
//            Category c4 = new Category(4L, "Social Media");
//            Category c5 = new Category(5L, "Branding");
//            Category c6 = new Category(6L, "Web Design");
//            Category c7 = new Category(7L, "App Design");
//            Category c8 = new Category(8L, "Advertising");
//            Category c9 = new Category(9L, "Packaging");
//            Category c10 = new Category(10L, "Promotional Material");
//            Category c11 = new Category(11L, "UI/UX");
//            Category c12 = new Category(12L, "Events");
//            Category c13 = new Category(13L, "Sales");
//            Category c14 = new Category(14L, "Merchandise");
//            repository.saveAll(List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14));
//        };
//    }
//}
