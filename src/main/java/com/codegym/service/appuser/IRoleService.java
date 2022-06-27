package com.codegym.service.appuser;

import com.codegym.model.user.Role;
import com.codegym.model.user.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
