package com.codegym.service.transaction;

import com.codegym.model.Transaction;
import com.codegym.service.IGeneralService;

import java.time.LocalDateTime;

public interface ITransactionService extends IGeneralService<Transaction> {
    Iterable<Transaction> findAllByOrderByCreatedDate();
    Iterable<Transaction> findAllByCreatedDateBetween(LocalDateTime fromTime, LocalDateTime toTime);

    Iterable<Transaction> findAllByWalletIdAndCreatedDateBetween(Long id, LocalDateTime fromTime, LocalDateTime toTime);
}
