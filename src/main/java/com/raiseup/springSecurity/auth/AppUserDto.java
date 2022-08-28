package com.raiseup.springSecurity.auth;

import java.util.Optional;


public interface AppUserDto {

    Optional<AppUser>selectApplicationUserByUsername(String username);
}
