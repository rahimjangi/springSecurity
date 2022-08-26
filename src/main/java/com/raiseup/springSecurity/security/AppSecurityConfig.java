package com.raiseup.springSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
//                .antMatchers("/api/**")
//                .hasRole(AppUserRole.GUEST.name())//Role base AUTH
                .antMatchers("/api/v1/**").hasAnyRole( AppUserRole.CUSTOMUSER.name(),AppUserRole.GUEST.name(),AppUserRole.MANAGER.name())
                .antMatchers(HttpMethod.PUT,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_READ.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
             .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userRahim = User
                .builder()
                .username("rahim")
                .password(passwordEncoder.encode("password"))
//                .roles(AppUserRole.GUEST.name())
                .authorities(AppUserRole.GUEST.getGrantedAuthorities())
                .build();

        UserDetails adminUser = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
//                .roles(AppUserRole.MANAGER.name())
                .authorities(AppUserRole.MANAGER.getGrantedAuthorities())
                .build();
        UserDetails customUser = User
                .builder()
                .username("custom")
                .password(passwordEncoder.encode("password"))
//                .roles(AppUserRole.CUSTOMUSER.name())
                .authorities(AppUserRole.CUSTOMUSER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(userRahim,adminUser,customUser);
    }

}


