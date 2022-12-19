package br.com.newgo.spring.shopng.services;

import br.com.newgo.spring.shopng.models.Product;
import br.com.newgo.spring.shopng.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductService {
    final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public boolean existsByUpc(String upc) {
        return productRepository.existsByUpc(upc);
    }
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByDescription(String description) {
        return productRepository.findByDescription(description);
    }

    public Optional<Product> findByUpc(String productUpc) {
        return productRepository.findByUpc(productUpc);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }
}
