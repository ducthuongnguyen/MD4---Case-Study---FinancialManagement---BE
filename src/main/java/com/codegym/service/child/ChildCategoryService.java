package com.codegym.service.child;

import com.codegym.model.ChildCategory;
import com.codegym.model.ParentCategory;
import com.codegym.repository.IChildCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildCategoryService implements IChildCategoryService{

    @Autowired
    public IChildCategoryRepository categoryRepository;
    @Override
    public Iterable<ChildCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<ChildCategory> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public ChildCategory save(ChildCategory childCategory) {
        return categoryRepository.save(childCategory);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Iterable<ChildCategory> findAllByParentCategory(ParentCategory parentCategory) {
        return categoryRepository.findAllByParentCategory(parentCategory);
    }
}
