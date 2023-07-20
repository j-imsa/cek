package ir.jimsa.cek.controller;

import ir.jimsa.cek.model.dto.UserDto;
import ir.jimsa.cek.model.request.UserRequest;
import ir.jimsa.cek.model.response.UserResponse;
import ir.jimsa.cek.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String checkStatus() {
        return "users GET method works!";
    }

    @PostMapping
    public ResponseEntity<UserResponse> addNewUser(@Valid @RequestBody UserRequest userRequest) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = userService.addUser(modelMapper.map(userRequest, UserDto.class));
        return new ResponseEntity<>(modelMapper.map(userDto, UserResponse.class), HttpStatus.CREATED);
    }
}
