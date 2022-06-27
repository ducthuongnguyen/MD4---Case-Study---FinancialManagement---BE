package com.codegym.controller;

import com.codegym.model.ChildCategory;
import com.codegym.model.ParentCategory;
import com.codegym.service.category.IChildCategoryService;
import com.codegym.service.category.IParentCategoryService;
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
    private IChildCategoryService childCategoryService;

    @Autowired
    private IParentCategoryService parentCategoryService;

    @GetMapping
    public ResponseEntity<Iterable<ChildCategory>> showAllCategory() {
        return new ResponseEntity<>(childCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ChildCategory>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(childCategoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/category-by-user")
    public ResponseEntity showUserCategory() {
        return new ResponseEntity(childCategoryService.showExpenseCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Optional<ChildCategory>> save(@RequestBody ChildCategory childCategory) {
        return new ResponseEntity(childCategoryService.save(childCategory), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Optional<ChildCategory>> update(@RequestParam Long id, @RequestBody ChildCategory childCategory) {
        Optional<ChildCategory> categoryOptional = childCategoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        childCategory.setId(categoryOptional.get().getId());
        return new ResponseEntity(childCategoryService.save(childCategory), HttpStatus.OK);

    }

    @GetMapping("/getChildCategory/{id}")
    public ResponseEntity<Iterable<ChildCategory>> getChildCategoryByParentCategory(@PathVariable("id") Long id) {
        Optional<ParentCategory> parentCategory1 = parentCategoryService.findById(id);
        if (!parentCategory1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<ChildCategory> childCategories = childCategoryService.findAllByParentCategory(parentCategory1.get());
        return new ResponseEntity<>(childCategories, HttpStatus.OK);

    }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<Optional<ChildCategory>> findById(@PathVariable Long id) {
//            Optional<ChildCategory> childCategory = childCategoryService.findById(id);
//            return new ResponseEntity<>(childCategory, HttpStatus.OK);
//        }
}
