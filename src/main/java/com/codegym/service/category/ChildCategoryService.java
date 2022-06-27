package com.codegym.service.category;

import com.codegym.model.ChildCategory;
import com.codegym.model.ExpenseCategory;
import com.codegym.model.ParentCategory;
import com.codegym.repository.ICategoryRepository;
import com.codegym.repository.IChildCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.Optional;

@Service
public class ChildCategoryService implements IChildCategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    IChildCategoryRepository childCategoryRepository;

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
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<ExpenseCategory> showExpenseCategories() {
        return categoryRepository.showExpenseCategories();
    }

    @Override
    public Iterable<ChildCategory> findAllByParentCategory(ParentCategory parentCategory) {
        return childCategoryRepository.findAllByParentCategory(parentCategory);
    }
}
