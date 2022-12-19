package br.com.newgo.spring.shopng.repositories;

import br.com.newgo.spring.shopng.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByUpc(String upc);

    List<Product> findByName(String name);

    List<Product> findByDescription(String description);

    Optional<Product> findByUpc(String productUpc);
}
