package br.com.newgo.spring.shopng.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
