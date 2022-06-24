package com.codegym.controller.user;

import com.codegym.dto.request.LogInForm;
import com.codegym.dto.request.SignUpForm;
import com.codegym.dto.response.JwtResponse;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.user.AppUser;
import com.codegym.model.user.ChangePassword;
import com.codegym.secirity.jwt.JwtAuthTokenFilter;
import com.codegym.secirity.jwt.JwtProvider;
import com.codegym.secirity.userprinciple.UserPrinciple;
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
        if (appUserService.existsByName(signUpForm.getName())) {
            return new ResponseEntity<>(new ResponseMessage("no_user"),
                    HttpStatus.BAD_REQUEST);
        }
        if (appUserService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("no_email"),
                    HttpStatus.BAD_REQUEST);
        }  // Creating user's account
        if (signUpForm.getPassword().equals(signUpForm.getPasswordConfirm())){
            AppUser user = new AppUser(signUpForm.getName(), signUpForm.getEmail(),
                    passwordEncoder.encode(signUpForm.getPassword()), passwordEncoder.encode(signUpForm.getPasswordConfirm()));
            appUserService.save(user);
        }else {
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
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<AppUser> editProfile(@RequestBody AppUser appUser,@PathVariable Long id ){
//        Optional<AppUser> userOptional=appUserService.findById(id);
//        if (!userOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        appUser.setId(userOptional.get().getId());
//        appUserService.save(appUser);
//        return new ResponseEntity<>(appUser,HttpStatus.OK);
//    }
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePassword changePassword){
        String jwt = JwtAuthTokenFilter.getJwt(request)   ;
        String emailUser = jwtProvider.getEmailFromJwtToken(jwt);
        AppUser appUser;

        try {
            appUser = appUserService.findUserByEmail(emailUser).orElseThrow(()-> new UsernameNotFoundException(" Not Found  -> email "+emailUser));
            boolean matches = passwordEncoder.matches(changePassword.getCurrentPassword(), appUser.getPassword());
            if(changePassword.getNewPassword() != null){
                if(matches){
                    appUser.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                    appUserService.save(appUser);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
