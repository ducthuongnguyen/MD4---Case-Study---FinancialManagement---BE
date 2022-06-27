package com.codegym.service.appuser;

import com.codegym.model.user.Role;
import com.codegym.model.user.RoleName;
import com.codegym.repository.userRepo.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}