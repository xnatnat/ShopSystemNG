package br.com.newgo.spring.shopng.configs.filters;

import br.com.newgo.spring.shopng.services.security.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * Trata todas as requisições direcionadas aos endpoints que não seja o de login,
 * i.e., responsável por verificar a autenticação do usuário a fim de autorizar acesso aos endpoints da aplicação.
 */
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest)servletRequest);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        /* adiciona o filtro na cadeia de filtros utilizada pelo spring security */
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
