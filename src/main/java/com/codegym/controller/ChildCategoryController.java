package com.codegym.controller;


import com.codegym.model.ChildCategory;
import com.codegym.model.ParentCategory;
import com.codegym.model.Wallet;
import com.codegym.repository.IChildCategoryRepository;
import com.codegym.service.child.IChildCategoryService;
import com.codegym.service.parent.IParentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/categories")
public class ChildCategoryController {
    @Autowired
    private IChildCategoryService childCategoryService;

    @Autowired
    private IParentCategoryService parentCategoryService;



    @GetMapping("/getChildCategory/{id}")
    public ResponseEntity<Iterable<ChildCategory>> getChildCategoryByParentCategory(@PathVariable("id") Long id) {
        Optional<ParentCategory> parentCategory1 = parentCategoryService.findById(id);
        if (!parentCategory1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<ChildCategory> childCategories = childCategoryService.findAllByParentCategory(parentCategory1.get());
        return new ResponseEntity<>(childCategories, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ChildCategory>> findById(@PathVariable Long id) {
        Optional<ChildCategory> childCategory = childCategoryService.findById(id);
        return new ResponseEntity<>(childCategory, HttpStatus.OK);
    }
}
