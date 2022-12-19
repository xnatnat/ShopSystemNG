package br.com.newgo.spring.shopng.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductListDto {
    @NotNull
    private String productUpc;
    @NotNull
    private long quantity;
}
