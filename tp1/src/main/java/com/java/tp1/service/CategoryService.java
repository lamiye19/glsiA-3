package com.java.tp1.service;

import com.java.tp1.model.Category;
import com.java.tp1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
    @Autowired
    private com.java.tp1.repository.CategoryRepository CategoryRepository;

    public void saveCategory(Category category){
        CategoryRepository.save(category);
    }

    public List<Category> showAllCategory(){
        return CategoryRepository.findAll();
    }

    public Category selectedCategory(int id){
        Optional<Category> optional = CategoryRepository.findById(id);
        Category category = null;
        if (optional.isPresent()){
            category = optional.get();
        }
        else {
            throw new RuntimeException("id introuvable");
        }

        return category;
    }

    public void deleteCategory(int id){
        CategoryRepository.deleteById(id);
    }
}
