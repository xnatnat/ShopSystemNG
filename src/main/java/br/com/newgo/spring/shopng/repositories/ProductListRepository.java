package br.com.newgo.spring.shopng.repositories;

import br.com.newgo.spring.shopng.models.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductListRepository extends JpaRepository<ProductList, UUID> {
}
