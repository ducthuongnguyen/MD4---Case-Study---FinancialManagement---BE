package com.codegym.service.wallet;

import com.codegym.model.Wallet;
import com.codegym.model.user.AppUser;
import com.codegym.service.IGeneralService;

public interface IWalletService extends IGeneralService<Wallet> {
    Iterable<Wallet> findAllByAppUser(AppUser appUser);
    Iterable<Wallet> findAllByNameContaining(String name);
}
