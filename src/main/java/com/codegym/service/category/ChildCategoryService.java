package com.codegym.service.category;

import com.codegym.model.ChildCategory;
import com.codegym.model.ExpenseCategory;
import com.codegym.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChildCategoryService implements IChildCategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

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
}
