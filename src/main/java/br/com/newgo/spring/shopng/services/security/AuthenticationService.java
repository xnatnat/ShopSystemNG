package br.com.newgo.spring.shopng.services.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

/**
 *  Service responsável por manipular os tokens jwt
 *
 */
public class AuthenticationService {
    /* Tempo de expiração do token. Definido para 24h */
    static final long EXPIRATIONTIME = 864_000_00;
    /* Chave secreta da assinatura.
     Serve para verificar se a assinatura é válida (se o token não foi corrompido entre o tráfego cliente-servidor),
     e então permitir que os dados sejam extraídos da criptografia.
     */
    static final String SIGNINGKEY = "SecretKey";
    /* Define o esquema de HTTP que será utilizado */
    static final String PREFIX = "Bearer";

    /**
     * Constroi o token jwt e o adiciona no cabeçalho das requisições HTTP.
     * Devolve o token para o usuário uma única vez, no momento da sua autenticação.
     * @param res
     * @param email
     */
    static public void addToken(HttpServletResponse res, String email){
        String JwtToken = Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact();
        res.addHeader("Authorization", PREFIX + " " + JwtToken);
        /* Indica que o item authorization será exposto no cabeçalho de todas as requisições */
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    /**
     *  Verifica token obtido através da requisição http
     * @param request
     * @return
     */
    static public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null) {
            String email = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (email != null)
                return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }
        return null;
    }
}
