package com.codegym.service.appuser;

import com.codegym.model.user.AppUser;
import com.codegym.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IAppUserService extends IGeneralService<AppUser> {
    Optional<AppUser> findByName(String name);

    Boolean existsByEmail(String email);
    Boolean existsByName(String name);

    Iterable<AppUser> findUsersByNameContaining(String name);
    AppUser save(AppUser appUser);
    Optional<AppUser> findUserByEmail(String email);
}
