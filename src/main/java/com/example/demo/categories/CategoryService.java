package com.example.demo.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    final private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean checkIfExists(Long id){
        return categoryRepository.findById(id).isPresent();
    }

    public Category getCategoryById(Long id){return categoryRepository.findById(id).get();}
}
