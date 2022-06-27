package com.codegym.repository;

import com.codegym.model.ChildCategory;
import com.codegym.model.ParentCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChildCategoryRepository extends PagingAndSortingRepository<ChildCategory,Long> {
    Iterable<ChildCategory> findAllByParentCategory(ParentCategory parentCategory);
}
