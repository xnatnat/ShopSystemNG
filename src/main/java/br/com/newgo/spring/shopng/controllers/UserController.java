package br.com.newgo.spring.shopng.controllers;

import br.com.newgo.spring.shopng.dtos.CreateUserDto;
import br.com.newgo.spring.shopng.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid CreateUserDto userDto){
        if(userService.existsByEmail(userDto.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Registered email.");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
    }


}
