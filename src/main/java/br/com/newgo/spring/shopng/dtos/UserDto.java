package br.com.newgo.spring.shopng.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    @NotBlank
    private String email;
    @NotNull
    private UUID id;

}
