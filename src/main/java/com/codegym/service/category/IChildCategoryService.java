package com.codegym.service.category;

import com.codegym.model.ChildCategory;
import com.codegym.model.ExpenseCategory;
import com.codegym.model.ParentCategory;
import com.codegym.service.IGeneralService;

public interface IChildCategoryService extends IGeneralService<ChildCategory> {
    Iterable<ExpenseCategory> showExpenseCategories();
    Iterable<ChildCategory> findAllByParentCategory(ParentCategory parentCategory);
}
