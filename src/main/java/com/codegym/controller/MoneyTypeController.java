package com.codegym.controller;

import com.codegym.model.MoneyType;
import com.codegym.service.moneytype.IMoneyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/moneytypes")
public class MoneyTypeController {
    @Autowired
    private IMoneyTypeService moneyTypeService;

    @GetMapping
    public ResponseEntity<Iterable<MoneyType>> findAll() {
        return new ResponseEntity<>(moneyTypeService.findAll(), HttpStatus.OK);
    }
}
