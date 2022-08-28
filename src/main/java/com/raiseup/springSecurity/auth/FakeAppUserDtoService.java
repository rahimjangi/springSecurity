package com.raiseup.springSecurity.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.raiseup.springSecurity.security.AppUserRole.*;

@Repository("fake")
public class FakeAppUserDtoService implements AppUserDto {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeAppUserDtoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectApplicationUserByUsername(String username) {
        return getAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    private List<AppUser>getAppUsers(){
        List<AppUser>appUsers= Lists.newArrayList(
            new AppUser(
                    "rahim",
                    passwordEncoder.encode("password"),
                    GUEST.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new AppUser(
                    "admin",
                    passwordEncoder.encode("password"),
                    MANAGER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new AppUser(
                    "customer",
                    passwordEncoder.encode("password"),
                    CUSTOMER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            )
        );
        return appUsers;
    }
}
