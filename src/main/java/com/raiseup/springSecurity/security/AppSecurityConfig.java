package com.raiseup.springSecurity.security;

import com.raiseup.springSecurity.auth.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    public AppSecurityConfig(PasswordEncoder passwordEncoder, AppUserService appUserService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* Basic AUTH
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
//                .antMatchers("/api/**")
//                .hasRole(AppUserRole.GUEST.name())//Role base AUTH
//                .antMatchers("/api/v1/**").hasAnyRole( AppUserRole.CUSTOMUSER.name(),AppUserRole.GUEST.name(),AppUserRole.MANAGER.name())
//                .antMatchers(HttpMethod.PUT,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_READ.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/api/v1/**").hasAuthority(AppUserPermission.MANAGER_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
             .httpBasic();
             */

        //Form Based Auth
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/products",true)
//                    .passwordParameter("password")
//                    .usernameParameter("username")
//                .and()
//                    .rememberMe().tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(30))
//                    .key("oiyuaiadlkjvlasd")
//                    .rememberMeParameter("remember-me")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID","remember-me")
                        .logoutSuccessUrl("/login");
    }

/* In memory user
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
                .authorities(AppUserRole.CUSTOMER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(userRahim,adminUser,customUser);
    }

 */




}


