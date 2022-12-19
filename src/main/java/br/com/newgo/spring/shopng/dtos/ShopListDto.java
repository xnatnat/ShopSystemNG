package br.com.newgo.spring.shopng.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ShopListDto {
    @NotNull
    private UUID userId;
    @NotEmpty
    private Set<ProductListDto> products;
}
