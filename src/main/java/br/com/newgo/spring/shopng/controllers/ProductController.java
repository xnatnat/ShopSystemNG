package br.com.newgo.spring.shopng.controllers;

import br.com.newgo.spring.shopng.dtos.ProductDto;
import br.com.newgo.spring.shopng.models.Product;
import br.com.newgo.spring.shopng.services.ProductService;
import br.com.newgo.spring.shopng.storage.Disk;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    final ProductService productService;
    final Disk disk;

    public ProductController(ProductService productService, Disk disk) {
        this.productService = productService;
        this.disk = disk;
    }

    @PostMapping(
        consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> save(@Valid @RequestPart("product") ProductDto productDto,
                                       @RequestPart("file")MultipartFile image){
        if(productService.existsByUpc(productDto.getUpc()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Registered Product.");
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setImageName(disk.saveImage(image));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<Product> product = productService.findById(id);
       if(!product.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> getByName(@PathVariable(value = "name") String name){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(name));
    }

    @GetMapping("/description")
    public ResponseEntity<List<Product>> getByDescription(@PathVariable(value = "description") String description){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByDescription(description));
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id){
        Optional<Product> product = productService.findById(id);
        if(!product.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        productService.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping(value = {"/{id}"},
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                           @RequestPart @Valid ProductDto productDto,
                                            @RequestPart("file")MultipartFile image){
        Optional<Product>productOptional =productService.findById(id);
        if(!productOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        Product product =new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setId(productOptional.get().getId());
        product.setImageName(disk.saveImage(image));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping({"/status/{id}"})
    public ResponseEntity<Object> updateStatus(@PathVariable(value = "id") UUID id,
                                               @RequestBody boolean isActive){
        Optional<Product>productOptional =productService.findById(id);
        if(!productOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        productOptional.get().setActive(isActive);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                                                productService.save(productOptional.get()));
    }
}
