package com.codegym.service.transaction;

import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import com.codegym.service.IGeneralService;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ITransactionService extends IGeneralService<Transaction> {
    Iterable<Transaction> findAllByOrderByCreatedDate();

    Iterable<Transaction> findAllByWallet(Wallet wallet);

    Iterable<Transaction> findAllByWalletOrderByCreatedDateDesc(Wallet wallet);

    Iterable<Transaction> findAllByCreatedDateBetween(Long walletId,LocalDateTime from, LocalDateTime to);
}
