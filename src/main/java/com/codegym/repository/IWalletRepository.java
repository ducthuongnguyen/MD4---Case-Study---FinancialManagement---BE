package com.codegym.repository;

import com.codegym.model.Wallet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends PagingAndSortingRepository<Wallet,Long> {
}
