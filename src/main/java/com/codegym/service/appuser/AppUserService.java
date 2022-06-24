package com.codegym.service.appuser;

import com.codegym.model.user.AppUser;
import com.codegym.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    IAppUserRepository appUserRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    public Optional<AppUser> findByName(String name) {
        return appUserRepository.findByNameContaining(name);
    }

    public Boolean existsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }

    public Boolean existsByName(String name) {
        return appUserRepository.existsByName(name);
    }

    public Iterable<AppUser> findUsersByNameContaining(String name) {
        return appUserRepository.findUsersByNameContaining(name);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepository.findAppUserByEmail(email);
    }

    @Override
    public void remove(Long id) {
        appUserRepository.deleteById(id);
    }
}

