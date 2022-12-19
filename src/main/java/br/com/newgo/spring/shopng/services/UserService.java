package br.com.newgo.spring.shopng.services;

import br.com.newgo.spring.shopng.dtos.CreateUserDto;
import br.com.newgo.spring.shopng.dtos.UserDto;
import br.com.newgo.spring.shopng.models.User;
import br.com.newgo.spring.shopng.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    final UserRepository userRepository;
    final ModelMapper modelMapper;
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
    public UserDto findByEmail(String email){
        return modelMapper.map(userRepository.findByEmail(email), UserDto.class);
    }
    public CreateUserDto findUserByEmail(String email){
        return modelMapper.map(userRepository.findByEmail(email), CreateUserDto.class);
    }
    @Transactional
    public UserDto save(CreateUserDto userDto){
        return modelMapper.map(
                userRepository.save(new User(userDto.getEmail(), userDto.getPassword())),
                UserDto.class);
    }
    @Transactional
    public void delete(UserDto userDto){
        userRepository.deleteByEmail(userDto.getEmail());
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
