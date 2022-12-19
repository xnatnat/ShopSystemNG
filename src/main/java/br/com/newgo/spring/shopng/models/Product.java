package br.com.newgo.spring.shopng.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Entity
@Table(name = "sng_products")
@Getter
@Setter
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, unique = true, length = 12)
    private String upc; //Universal Product Code
    @Column(nullable = false)
    private String imageName;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "product")
    private List<ProductList> productLists;
}