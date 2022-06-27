package com.codegym.repository;

import com.codegym.model.Wallet;
import com.codegym.model.user.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends PagingAndSortingRepository<Wallet,Long> {
    Iterable<Wallet> findAllByAppUser(AppUser appUser);
    Iterable<Wallet> findAllByNameContaining(String name);
}
