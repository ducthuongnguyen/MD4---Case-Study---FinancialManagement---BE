package com.codegym.repository;

import com.codegym.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByNameContaining(String name);
    Optional<AppUser> findByEmailContaining(String email);

    Boolean existsByEmail(String email);
    Boolean existsByName(String name);

    Iterable<AppUser> findUsersByNameContaining(String name);
    Optional<AppUser> findAppUserByEmail(String email);
}