package com.codegym.security.userprinciple;

import com.codegym.model.user.AppUser;
import com.codegym.repository.userRepo.IAppUserRepository;
import com.codegym.service.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IAppUserRepository userRepository;
    @Autowired
    AppUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password" + username));
        return UserPrinciple.build(user);
    }

}