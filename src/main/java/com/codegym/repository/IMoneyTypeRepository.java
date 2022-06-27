package com.codegym.repository;

import com.codegym.model.MoneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoneyTypeRepository extends JpaRepository<MoneyType,Long> {

}