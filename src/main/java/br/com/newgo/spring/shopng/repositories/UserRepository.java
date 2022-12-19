package br.com.newgo.spring.shopng.repositories;

import br.com.newgo.spring.shopng.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    void deleteByEmail(String email);
}
