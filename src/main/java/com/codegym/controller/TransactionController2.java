package com.codegym.controller;

import com.codegym.model.Transaction;
import com.codegym.service.transaction.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("*")
public class TransactionController2 {
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/users/findCreateDateBetween")
    public ResponseEntity<Iterable<Transaction>> findAllByCreatedDateBetween(@RequestParam String fromTime, @RequestParam String toTime){
        if(fromTime.equals("") && toTime.equals("")){
            fromTime = "1900-01-01T00:00:00";
            toTime = String.valueOf(LocalDateTime.now());
        }
        return new ResponseEntity<>(transactionService.findAllByCreatedDateBetween(LocalDateTime.parse(fromTime), LocalDateTime.parse(toTime)), HttpStatus.OK);
    }

    @GetMapping("/users/findAllByCreatedDate/{id}")
    public ResponseEntity<Iterable<Transaction>> findAllByWalletIdAndCreatedDateBetween(@PathVariable Long id, @RequestParam String fromTime,@RequestParam String toTime){
        if(fromTime.equals("") && toTime.equals("")){
            fromTime = "1900-01-01T00:00:00";
            toTime = String.valueOf(LocalDateTime.now());
        }
        return new ResponseEntity<>(transactionService.findAllByWalletIdAndCreatedDateBetween('%'+ id +'%' ,LocalDateTime.parse(fromTime), LocalDateTime.parse(toTime)), HttpStatus.OK);
    }
}
