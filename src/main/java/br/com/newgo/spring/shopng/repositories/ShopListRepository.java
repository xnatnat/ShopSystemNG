package br.com.newgo.spring.shopng.repositories;

import br.com.newgo.spring.shopng.models.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopListRepository extends JpaRepository<ShopList, UUID> {

}
