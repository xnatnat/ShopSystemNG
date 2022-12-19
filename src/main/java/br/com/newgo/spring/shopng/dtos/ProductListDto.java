package br.com.newgo.spring.shopng.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ProductListDto {
    @NotNull
    private String productUpc;
    @NotNull
    private long quantity;
}
