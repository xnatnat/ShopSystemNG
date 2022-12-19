package br.com.newgo.spring.shopng.configs;

import br.com.newgo.spring.shopng.configs.filters.AuthenticationFilter;
import br.com.newgo.spring.shopng.configs.filters.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/* Classe criada para utilizar o spring security 5.7 */
@Configuration
@EnableWebSecurity
public class SecurityConfigV2 {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .requestMatchers("/api/v1/product").hasRole("ADMIN")
                .requestMatchers("/api/v1/shopList").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LoginFilter("/login", authenticationManager()),	UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                .csrf().disable();
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService)
                    .passwordEncoder(new BCryptPasswordEncoder());

    }
}
