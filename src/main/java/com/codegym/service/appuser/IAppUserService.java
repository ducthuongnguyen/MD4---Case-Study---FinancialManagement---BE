package com.codegym.service.appuser;

import com.codegym.model.user.AppUser;
import com.codegym.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IAppUserService extends IGeneralService<AppUser> {
    Optional<AppUser> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    AppUser save(AppUser user);
}
