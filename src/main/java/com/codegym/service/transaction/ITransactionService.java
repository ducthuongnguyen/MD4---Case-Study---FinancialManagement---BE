package com.codegym.service.transaction;

import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import com.codegym.service.IGeneralService;

import java.time.LocalDateTime;


public interface ITransactionService extends IGeneralService<Transaction> {
    Iterable<Transaction> findAllByOrderByCreatedDate();

    Iterable<Transaction> findAllByCreatedDateBetween(LocalDateTime fromTime, LocalDateTime toTime);

    Iterable<Transaction> findAllByWalletIdAndCreatedDateBetween(Long id, LocalDateTime fromTime, LocalDateTime toTime);

    Iterable<Transaction> findAllByWallet(Wallet wallet);

    Iterable<Transaction> findAllByWalletOrderByCreatedDateDesc(Wallet wallet);

//    Iterable<Transaction> findAllByCreatedDateBetween(Long walletId, LocalDateTime from, LocalDateTime to);
}
