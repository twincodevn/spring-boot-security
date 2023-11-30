package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecureConfigure {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE","MANAGER").build();
        UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE","MANAGER","ADMIN").build();
        return new InMemoryUserDetailsManager(john,mary);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configures -> configures.anyRequest().authenticated()).formLogin(
                form -> form.loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll()
        );
        return http.build();
    }

}
