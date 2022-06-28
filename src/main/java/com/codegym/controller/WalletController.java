package com.codegym.controller;


import com.codegym.model.Transaction;
import com.codegym.model.Wallet;
import com.codegym.model.user.AppUser;
import com.codegym.security.jwt.JwtAuthTokenFilter;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.service.appuser.AppUserService;
import com.codegym.service.appuser.IAppUserService;
import com.codegym.service.appuser.RoleServiceImpl;
import com.codegym.service.transaction.ITransactionService;
import com.codegym.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/wallets")
public class WalletController {
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
    @Autowired
    private IWalletService walletService;
    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IAppUserService appUserService;

    @GetMapping
    public ResponseEntity<Iterable<Wallet>> findAllWallet() {
        Iterable<Wallet> wallets = walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Wallet>> findById(HttpServletRequest request ,@PathVariable Long id) {
//        String jwt = jwtTokenFilter.getJwt(request);
//        String username = jwtProvider.getUerNameFromToken(jwt);
//        AppUser user;
//        user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username"+username));
//        if (user==null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        Optional<Wallet> wallets = walletService.findById(id);
//        return new ResponseEntity<>(wallets, HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody Wallet wallet) {
//        Optional<Wallet> wallet1 = walletService.findById(id);
//        if (!wallet1.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        wallet.setId(wallet1.get().getId());
//        walletService.save(wallet);
//        return new ResponseEntity<>(HttpStatus.OK);


    @PostMapping("/create-wallet")
    public ResponseEntity<Wallet> createWallet(HttpServletRequest request ,@RequestBody Wallet wallet) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username"+username));
       if (user==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
//        Optional<AppUser> appUser = appUserService.findById(id);
//       if (!appUser.isPresent()){
//           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//        Wallet wallet1 = new Wallet(wallet.getName(), wallet.getIcon(), wallet.getMoneyAmount(),wallet.getMoneyType(),wallet.getAppUser());
//        if (wallet.getIcon() == null) {
//            wallet.setIcon("https://www.google.com/search?q=m%E1%BA%B7t+c%C6%B0%E1%BB%9Di+icon&rlz=1C1CHZN_viVN985VN985&sxsrf=ALiCzsZg4EtkDSAi2X8ZHoKEj8zjWqoa8g:1655949487936&tbm=isch&source=iu&ictx=1&vet=1&fir=j0eYT_KqdfmRWM%252CatKRQpvM7QKMXM%252C_%253BQP9FLm3QRBEtEM%252CEI6u80xuqzpYKM%252C_%253BuaoKC_-CfYowqM%252C-LTAiGYURlMZeM%252C_%253BBf_jguLH4jDniM%252CwAWhCvnULYK0WM%252C_%253Bhy3ccLlBIuHB_M%252CiZB0SfGcqJil3M%252C_%253B40Gqj-pdBkek1M%252CskKUgevJUY-dhM%252C_%253Bm29keyj8nnxIlM%252CPa0LxFuGdJgYdM%252C_%253BLk4bzQu3Gv4lyM%252C4IkGnnt1BmCaAM%252C_%253BzB9iQj7j3xBOrM%252C-FbRrT-WGrgN1M%252C_%253BxqSOmldUDGESCM%252CAwdVvGONzOL41M%252C_%253BzU0UEOleSCZ5JM%252CPSRlslIVrXKm8M%252C_%253B6YA3CLmbfZEbgM%252CtqC64nDgK4pWKM%252C_%253Boyx1c8K1vKWpTM%252C4J05n4F3nyHGVM%252C_%253BXjEDUX4k3yn9bM%252ClgLhpeS6OayrsM%252C_%253B-nP5WqjNEXm7nM%252Co8R439J-X19XOM%252C_&usg=AI4_-kRMehw2Vdjay--TF2cwQ4tkKQLEAg&sa=X&ved=2ahUKEwjh6PnHvML4AhWXplYBHY4uDgkQ9QF6BAgNEAE#imgrc=hy3ccLlBIuHB_M");
//        }
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
    }


    @PutMapping("/editWallet/{id}")
    public ResponseEntity<Wallet> editWallet(HttpServletRequest request ,@RequestBody Wallet wallet, @PathVariable Long id) {
        String username = userService.findById(wallet.getAppUser().getId()).get().getUsername();
        AppUser user;
        user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username"+username));
        if (user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wallet.setId(walletOptional.get().getId());
//        AppUser appUser = appUserService.findById(id).get();
//        Wallet wallet1 = new Wallet(wallet.getName(), wallet.getIcon(), wallet.getMoneyAmount(),wallet.getMoneyType(),wallet.getAppUser());
        return new ResponseEntity<>(walletService.save(wallet), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wallet> deleteWallet(HttpServletRequest request ,@PathVariable Long id) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username"+username));
        if (user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Wallet> walletOptional = walletService.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletService.remove(id);
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findByUser/{id}")
    public ResponseEntity<Iterable<Wallet>> findAllByAppUserId(HttpServletRequest request,@PathVariable Long id) {
        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        AppUser user;
        user = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username"+username));
        if (user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<AppUser> userOptional = appUserService.findById(id);
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Wallet> wallets = walletService.findAllByAppUser(userOptional.get());
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Iterable<Wallet>> findByWalletName(@PathVariable String name){
        return new ResponseEntity<>(walletService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/transaction-by-wallet/{id}")
    public ResponseEntity<Iterable<Transaction>> getTransactionByWallet(@PathVariable("id") Long id) {
        Optional<Wallet> wallet = walletService.findById(id);
        if (!wallet.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<Transaction> transactions = transactionService.findAllByWalletOrderByCreatedDateDesc(wallet.get());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Wallet>> findById(@PathVariable Long id) {
        Optional<Wallet> wallets= walletService.findById(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
}
