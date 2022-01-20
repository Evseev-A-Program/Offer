package com.example.offer.repository;

import com.example.offer.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {

   Category findByName(String name);
}
