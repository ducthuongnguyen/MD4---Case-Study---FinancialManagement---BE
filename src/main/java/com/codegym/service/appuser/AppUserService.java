package com.codegym.service.appuser;

import com.codegym.model.user.AppUser;
import com.codegym.repository.userRepo.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    IAppUserRepository appUserRepository;

    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    public void remove(Long id) {
appUserRepository.deleteById(id);
    }

    public Optional<AppUser> findByUsername(String name) {
        return appUserRepository.findByUsername(name);
    }

    public Boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }

    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }
}

