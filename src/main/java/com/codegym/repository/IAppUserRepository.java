package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
}
