package br.com.newgo.spring.shopng.services;

import br.com.newgo.spring.shopng.models.ShopList;
import br.com.newgo.spring.shopng.repositories.ShopListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShopListService {
    final ShopListRepository shopListRepository;
    public ShopListService(ShopListRepository shopListRepository) {
        this.shopListRepository = shopListRepository;
    }
    @Transactional
    public ShopList save(ShopList shopList) {
        return shopListRepository.save(shopList);
    }
    public List<ShopList> findAll() {
        return null;
    }

    public Optional<ShopList> findById(UUID id) {
        return shopListRepository.findById(id);
    }
}
