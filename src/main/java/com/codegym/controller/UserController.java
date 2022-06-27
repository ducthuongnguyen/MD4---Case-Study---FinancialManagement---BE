package com.codegym.controller;

import com.codegym.dto.request.ChangeAvatar;
import com.codegym.dto.request.ChangePasswordForm;
import com.codegym.dto.request.LogInForm;
import com.codegym.dto.request.SignUpForm;
import com.codegym.dto.response.JwtResponse;
import com.codegym.dto.response.ResponseMessage;
import com.codegym.model.user.AppUser;
import com.codegym.model.user.Role;
import com.codegym.model.user.RoleName;
import com.codegym.security.jwt.JwtAuthTokenFilter;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.userprinciple.UserPrinciple;
import com.codegym.service.appuser.AppUserService;
import com.codegym.service.appuser.RoleServiceImpl;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    AppUserService userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtAuthTokenFilter jwtTokenFilter;
    @GetMapping("")
    private ResponseEntity<Iterable<AppUser>> findAll(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/findIdUser/{id}")
    private ResponseEntity<AppUser> findIdUser(@PathVariable Long id){
        Optional<AppUser> userOptional=userService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userOptional,HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("user_existed"), HttpStatus.OK);
        }
//        if(userService.existsByEmail(signUpForm.getEmail())){
//            return new ResponseEntity<>(new ResponseMessage("email_existed"), HttpStatus.OK);
//        }
        if(signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()){
            signUpForm.setAvatar("https://kimconcept.com/wp-content/uploads/2022/03/sticker-cute-la-chona-29.png");
        }
        AppUser user = new AppUser(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),passwordEncoder.encode(signUpForm.getPassword()),signUpForm.getAvatar());
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LogInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(userPrinciple.getId(),token, userPrinciple.getName(),userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @Valid @RequestBody ChangePasswordForm changePasswordForm){
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        try {
            user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username"+username));
            boolean matches = passwordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword());
            if(changePasswordForm.getNewPassword() != null){
                if(matches){
                    user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
                    userService.save(user);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest httpServletRequest, @Valid @RequestBody ChangeAvatar changeAvatar){
        String jwt = jwtTokenFilter.getJwt(httpServletRequest);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        try {
            if(changeAvatar.getAvatar()==null){
                return new ResponseEntity(new ResponseMessage("no"), HttpStatus.OK);
            } else{
                user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Fount with -> username:"+username));
                user.setAvatar(changeAvatar.getAvatar());
                userService.save(user);
            }
            return new ResponseEntity(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}

