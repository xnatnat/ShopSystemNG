package br.com.newgo.spring.shopng.controllers;

import br.com.newgo.spring.shopng.dtos.ProductListDto;
import br.com.newgo.spring.shopng.dtos.ShopListDto;
import br.com.newgo.spring.shopng.models.Product;
import br.com.newgo.spring.shopng.models.ProductList;
import br.com.newgo.spring.shopng.models.ShopList;
import br.com.newgo.spring.shopng.models.User;
import br.com.newgo.spring.shopng.services.ProductListService;
import br.com.newgo.spring.shopng.services.ProductService;
import br.com.newgo.spring.shopng.services.ShopListService;
import br.com.newgo.spring.shopng.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api/v1/ShopLists")
public class ShopListController {
    final ShopListService shopListService;
    final UserService userService;
    final ProductService productService;
    final ProductListService productListService;
    public ShopListController(ShopListService shopListService,
                              UserService userService,
                              ProductService productService,
                              ProductListService productListService) {
        this.shopListService = shopListService;
        this.userService = userService;
        this.productService = productService;
        this.productListService = productListService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ShopListDto shopListDto){
        Optional<User> userOptional = userService.findById(shopListDto.getUserId());
        if(userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Unregistered User.");
        var shopList = new ShopList();
        shopList.setUser(userOptional.get());
        shopList.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        shopList = shopListService.save(shopList);
        Set<ProductList> productLists = this.getProductsListInstances(shopList, shopListDto.getProducts());
        if(productLists.isEmpty())
            //TODO fazer verificaçao em outro lugar e aplicando outra logica
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Unregistered Product.");
        shopList.setProducts(productLists);
        return ResponseEntity.status(HttpStatus.CREATED).body(shopListService.save(shopList));
    }

    private Set<ProductList> getProductsListInstances(ShopList shopList, Set<ProductListDto> productListDto){
        Set<ProductList> products = new HashSet<>();
        for(ProductListDto productDto: productListDto){
            Optional<Product> productOptional = productService.findByUpc(productDto.getProductUpc());
            if(productOptional.isEmpty())
                return new HashSet<>();
            //TODO: verificar se a quantidade de produto está disponível, subtrair a quantidade exigida e atualizar produto
            ProductList productList = new ProductList();
            productList.setProduct(productOptional.get());
            productList.setQuantity(productDto.getQuantity());
            productList.setShopList(shopList);
            products.add(productListService.save(productList));
        }
        return products;
    }

    @GetMapping
    public ResponseEntity<List<ShopList>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(shopListService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<ShopList> shopList = shopListService.findById(id);
        if(!shopList.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShopList not found.");
        return ResponseEntity.status(HttpStatus.OK).body(shopList.get());
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id){
        Optional<ShopList> shopList = shopListService.findById(id);
        if(!shopList.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShopList not found.");
        return ResponseEntity.status(HttpStatus.OK).body("ShopList deleted successfully.");
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid ShopListDto shopListDto){
        Optional<User> userOptional = userService.findById(shopListDto.getUserId());
        if(userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Unregistered User.");
        Optional<ShopList> shopListOptional =shopListService.findById(id);
        if(!shopListOptional.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShopList not found.");
        var shopList = new ShopList();
        BeanUtils.copyProperties(shopListDto, shopList);
        shopList.setId(shopListOptional.get().getId());
        shopList.setRegistrationDate(shopListOptional.get().getRegistrationDate());
        Set<ProductList> productLists = this.getProductsListInstances(shopList, shopListDto.getProducts());

        if(productLists.isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Unregistered Product.");
        shopList.setProducts(productLists);
        return ResponseEntity.status(HttpStatus.CREATED).body(shopListService.save(shopList));
    }
}
