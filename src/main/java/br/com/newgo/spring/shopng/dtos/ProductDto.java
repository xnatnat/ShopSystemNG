package br.com.newgo.spring.shopng.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    @Size(max = 12)
    private String upc; //Universal Product Code
    @NotBlank
    private String brand;
    @NotNull
    private long quantity;
    @NotNull
    private Double price;
}