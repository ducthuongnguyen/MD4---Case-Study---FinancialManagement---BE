package com.codegym.controller;


import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import com.codegym.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
