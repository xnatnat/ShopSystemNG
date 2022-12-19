package br.com.newgo.spring.shopng.services;

import br.com.newgo.spring.shopng.models.ProductList;
import br.com.newgo.spring.shopng.repositories.ProductListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductListService {
    final ProductListRepository productListRepository;
    public ProductListService(ProductListRepository productListRepository) {
        this.productListRepository = productListRepository;
    }
    @Transactional
    public ProductList save(ProductList productList) {
        return productListRepository.save(productList);
    }
}
