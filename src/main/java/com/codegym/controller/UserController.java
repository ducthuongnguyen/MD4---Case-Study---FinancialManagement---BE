package com.codegym.controller;

import com.codegym.dto.request.LogInForm;
import com.codegym.dto.request.SignUpForm;
import com.codegym.dto.response.JwtResponse;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.user.AppUser;
import com.codegym.model.user.ChangePassword;
import com.codegym.security.jwt.JwtAuthTokenFilter;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.userprinciple.UserPrinciple;
import com.codegym.service.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    AppUserService appUserService;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("")
    public ResponseEntity<Iterable<AppUser>> findAllUsers() {
        return new ResponseEntity<>(appUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody SignUpForm signUpForm) {
        if (appUserService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("existed_email"),
                    HttpStatus.BAD_REQUEST);
        }  // Creating user's account
        if (signUpForm.getPassword().equals(signUpForm.getPasswordConfirm())) {
            AppUser user = new AppUser(signUpForm.getName(), signUpForm.getEmail(),
                    passwordEncoder.encode(signUpForm.getPassword()), passwordEncoder.encode(signUpForm.getPasswordConfirm()));
            appUserService.save(user);
        } else {
            return new ResponseEntity<>(new ResponseMessage("password fail"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseMessage("successfully"), HttpStatus.OK);
    }

    @PostMapping("/logIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LogInForm logInForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInForm.getEmail(), logInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail(), userDetails.getAvatar(), userDetails.getPassword()
        ));

    }
}
