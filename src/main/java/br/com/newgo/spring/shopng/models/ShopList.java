package br.com.newgo.spring.shopng.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sng_shopList")
public class ShopList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @OneToMany(mappedBy = "shopList")
    private Set<ProductList> products;
    @ManyToOne
    @JoinColumn(name = "shopList_id", nullable = false)
    private User user;
}
