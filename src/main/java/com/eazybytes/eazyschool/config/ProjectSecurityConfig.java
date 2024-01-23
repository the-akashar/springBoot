package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg")
                        .ignoringRequestMatchers("/public/**")
                )
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated())
                .authorizeHttpRequests((requests) -> {

            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/","/home")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/courses")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/contact")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/about")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/assets/**")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/holidays/**")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/login/**")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/logout")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/saveMsg")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/public/**")).permitAll();
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/displayMessages")).hasRole("ADMIN");
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.requestMatchers("/closeMsg/**")).hasRole("ADMIN");





        });
        http.formLogin(loginConfigurer -> loginConfigurer
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error = true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                      .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());
        ;

        return (SecurityFilterChain)http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService(){

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("ADMIN")
                .build();





        return new InMemoryUserDetailsManager(user , admin);
    }
}


