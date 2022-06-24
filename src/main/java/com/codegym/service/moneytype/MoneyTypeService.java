package com.codegym.service.moneytype;

import com.codegym.model.MoneyType;
import com.codegym.repository.IMoneyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoneyTypeService implements IMoneyTypeService {
    @Autowired
    private IMoneyTypeRepository moneyTypeRepository;

    @Override
    public Iterable<MoneyType> findAll() {
        return moneyTypeRepository.findAll();
    }

    @Override
    public Optional<MoneyType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public MoneyType save(MoneyType moneyType) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
