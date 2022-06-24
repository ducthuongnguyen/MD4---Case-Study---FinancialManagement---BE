package com.codegym.service.child;

import com.codegym.model.ChildCategory;
import com.codegym.model.ParentCategory;
import com.codegym.service.IGeneralService;

public interface IChildCategoryService extends IGeneralService<ChildCategory> {
    Iterable<ChildCategory> findAllByParentCategory(ParentCategory parentCategory);
}
