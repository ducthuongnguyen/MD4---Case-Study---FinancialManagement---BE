package com.codegym.controller;

import com.codegym.model.ChildCategory;
import com.codegym.service.category.IChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/categories")
public class ChildCategoryController {
    @Autowired
    private IChildCategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<ChildCategory>> showAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ChildCategory>> findById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/category-by-user")
    public ResponseEntity showUserCategory() {
        return new ResponseEntity(categoryService.showExpenseCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Optional<ChildCategory>> save(@RequestBody ChildCategory childCategory) {
        return new ResponseEntity(categoryService.save(childCategory), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Optional<ChildCategory>> update(@RequestParam Long id, @RequestBody ChildCategory childCategory) {
        Optional<ChildCategory> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        childCategory.setId(categoryOptional.get().getId());
        return new ResponseEntity(categoryService.save(childCategory), HttpStatus.OK);

    }
}
