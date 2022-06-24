package com.codegym.repository;

import com.codegym.model.Wallet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IWalletRepository extends PagingAndSortingRepository<Wallet,Long> {
    Iterable<Wallet> findAllByAppUser_Id(Long id);

    Optional<Wallet>
}
