package br.com.newgo.spring.shopng.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateUserDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
