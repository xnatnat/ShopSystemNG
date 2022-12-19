package br.com.newgo.spring.shopng.dtos;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    @NotBlank
    private String email;
    @NotNull
    private UUID id;

}
