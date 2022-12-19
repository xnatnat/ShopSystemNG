package br.com.newgo.spring.shopng.services.security;

import br.com.newgo.spring.shopng.dtos.CreateUserDto;
import br.com.newgo.spring.shopng.dtos.UserDto;
import br.com.newgo.spring.shopng.services.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* Classe service criada para separar a configuração security das classes de negócio */
//TODO inserir roles
@Service
public class UserDetailsServiceSec implements UserDetailsService {

    final UserService userService;

    public UserDetailsServiceSec(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CreateUserDto currentUser = userService.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                email, currentUser.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
    }
}
