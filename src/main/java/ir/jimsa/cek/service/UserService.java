package ir.jimsa.cek.service;

import ir.jimsa.cek.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto addUser(UserDto userDto);
}
