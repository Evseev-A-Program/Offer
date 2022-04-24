package com.example.offer.controllers;

import com.example.offer.models.Category;
import com.example.offer.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get/all")
    public ResponseEntity getAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addOffer(@RequestBody Category category){
        try{
            categoryService.saveCategory(category);
            return ResponseEntity.ok("Category save");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/delete")
    public ResponseEntity deleteOffer(@RequestBody Long id){
        try{
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category delete");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
