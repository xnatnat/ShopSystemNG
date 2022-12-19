package br.com.newgo.spring.shopng.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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