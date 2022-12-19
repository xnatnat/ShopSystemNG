package br.com.newgo.spring.shopng.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Entity
@Table(name = "sng_productList")
@Getter
@Setter
public class ProductList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "shopList_id", nullable = false)
    private ShopList shopList;
}
