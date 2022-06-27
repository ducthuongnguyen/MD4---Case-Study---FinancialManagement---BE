package com.codegym.controller;


import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import com.codegym.service.transaction.ITransactionService;
import com.codegym.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    public IWalletService walletService;

    @Autowired
    public ITransactionService transactionService;

    @GetMapping
    public ResponseEntity<Iterable<Wallet>> findAllWallet() {
        Iterable<Wallet>  wallets= walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Wallet>> findById(@PathVariable Long id) {
        Optional<Wallet> wallets= walletService.findById(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
        Optional<Wallet> wallet1 = walletService.findById(id);
        if (!wallet1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(wallet1.get().getId());
        walletService.save(wallet);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/transaction-by-wallet/{id}")
    public ResponseEntity<Iterable<Transaction>> getTransactionByWallet(@PathVariable("id") Long id) {
        Optional<Wallet> wallet = walletService.findById(id);
        if (!wallet.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<Transaction> transactions = transactionService.findAllByWalletOrderByCreatedDateDesc(wallet.get());
        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }
}
