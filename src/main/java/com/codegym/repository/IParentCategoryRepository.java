package com.codegym.repository;

import com.codegym.model.ParentCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentCategoryRepository extends PagingAndSortingRepository<ParentCategory,Long> {
}
