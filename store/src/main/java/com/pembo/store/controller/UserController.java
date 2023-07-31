package com.pembo.store.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.UserDto;
import com.pembo.store.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
