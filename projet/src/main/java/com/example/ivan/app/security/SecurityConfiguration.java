package com.example.ivan.app.security;


import com.example.ivan.app.services.UserPrincipalDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailService userPrincipalDetailService;

    public SecurityConfiguration(UserPrincipalDetailService userPrincipalDetailService) {
        this.userPrincipalDetailService = userPrincipalDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .and()
                .withUser("vendeur").password(passwordEncoder().encode("vendeur123"))
                .roles("VENDEUR");
         */

        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/forgetpassword?**").permitAll()
                .antMatchers("/Users/updatepassword").permitAll()
                .antMatchers("/").authenticated()
                .antMatchers("/dashboard").authenticated()
                .antMatchers("/").hasAnyRole("ADMIN","VENDEUR")
                .antMatchers("/dashboard").hasAnyRole("ADMIN","VENDEUR")
                .antMatchers("/products/create").hasRole("ADMIN")
                .antMatchers("/products/save").hasRole("ADMIN")
                .antMatchers("/products/all").hasAnyRole("ADMIN","VENDEUR")
                .antMatchers("/categories/create").hasRole("ADMIN")
                .antMatchers("/categories/save").hasRole("ADMIN")
                .antMatchers("/categories/all").hasAnyRole("ADMIN","VENDEUR")
                .antMatchers("/provisionnings/**").hasAnyRole("ADMIN","VENDEUR")
                .antMatchers("/Users/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/dashboard", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .clearAuthentication(true).invalidateHttpSession(true)
                .and()
                .rememberMe().tokenValiditySeconds(2592000).key("mySecret").userDetailsService(userPrincipalDetailService);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailService);

        return daoAuthenticationProvider;
    }
}
