package com.codegym.repository;

import com.codegym.model.ChildCategory;
import com.codegym.model.ExpenseCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<ChildCategory,Long> {
    @Query(value = "select c.name, sum(t.money_amount) as 'total' from child_category c join transactions t on c.id = t.child_category_id and c.parent_category_id= 1 group by  c.id",nativeQuery = true)
    Iterable<ExpenseCategory> showExpenseCategories();
}
