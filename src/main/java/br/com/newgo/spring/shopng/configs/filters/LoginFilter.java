package br.com.newgo.spring.shopng.configs.filters;

import br.com.newgo.spring.shopng.dtos.CreateUserDto;
import br.com.newgo.spring.shopng.dtos.UserDto;
import br.com.newgo.spring.shopng.services.security.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

import static java.util.Collections.emptyList;

/**
 * Classe responsável por tratar todas as requisiçoes do endpoint login
 * Validar o login e senha e autenticar o usuario
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url,
                       AuthenticationManager authManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        CreateUserDto userDto = new ObjectMapper()
                .readValue(request.getInputStream(), CreateUserDto.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getEmail(), userDto.getPassword(), emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth)
            throws IOException, ServletException {
        AuthenticationService.addToken(response, auth.getName());
    }
}
