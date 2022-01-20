package com.example.offer.service;


import com.example.offer.models.Category;
import com.example.offer.repository.CategoryDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryDao categoryDao;

    public List<Category> findAllCategories(){
        return (List<Category>) categoryDao.findAll();
    }

    public void saveCategory(Category category){
            categoryDao.save(category);
    }
}
