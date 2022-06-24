package com.codegym.repository;

import com.codegym.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ITransactionRepository extends PagingAndSortingRepository<Transaction,Long> {
    Iterable<Transaction> findAllByCreatedDateBetween(LocalDateTime fromTime, LocalDateTime toTime);
    Iterable<Transaction> findAllByOrderByCreatedDateDesc();

    Iterable<Transaction> findAllByWalletIdAndCreatedDateBetween(Long id, LocalDateTime fromTime, LocalDateTime toTime);
}
