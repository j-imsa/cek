package ir.jimsa.cek.service.impl;

import ir.jimsa.cek.model.dto.UserDto;
import ir.jimsa.cek.model.entity.UserEntity;
import ir.jimsa.cek.repository.UserRepository;
import ir.jimsa.cek.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncodedPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserDto.class);
    }

}
