package com.example.demo;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("給料", "income"));
            categoryRepository.save(new Category("副業", "income"));
            categoryRepository.save(new Category("その他収入", "income"));
            categoryRepository.save(new Category("食費", "expense"));
            categoryRepository.save(new Category("住居費", "expense"));
            categoryRepository.save(new Category("光熱費", "expense"));
            categoryRepository.save(new Category("交通費", "expense"));
            categoryRepository.save(new Category("通信費", "expense"));
            categoryRepository.save(new Category("娯楽費", "expense"));
            categoryRepository.save(new Category("その他支出", "expense"));
        }
    }
}
