package br.com.newgo.spring.shopng.configs.filters;

import br.com.newgo.spring.shopng.services.security.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Trata todas as requisições direcionadas aos endpoints que não seja o de login,
 * i.e., responsável por verificar a autenticação do usuário a fim de autorizar acesso aos endpoints da aplicação.
 */
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest)servletRequest);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        /* adiciona o filtro na cadeia de filtros utilizada pelo spring security */
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
