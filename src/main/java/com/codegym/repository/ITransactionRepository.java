package com.codegym.repository;

import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ITransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    Iterable<Transaction> findAllByCreatedDateBetween(LocalDateTime fromTime, LocalDateTime toTime);

    Iterable<Transaction> findAllByOrderByCreatedDateDesc();

    Iterable<Transaction> findAllByWallet(Wallet wallet);

    Iterable<Transaction> findAllByWalletOrderByCreatedDateDesc(Wallet wallet);

//    @Query(value = "select * from transactions where wallet_id =:walletId and created_date between :from and :to", nativeQuery = true)
//    Iterable<Transaction> findAllByCreatedDateBetween(Long walletId, LocalDateTime from, LocalDateTime to);

    Iterable<Transaction> findAllByWalletIdAndCreatedDateBetween(Long id, LocalDateTime fromTime, LocalDateTime toTime);
}
