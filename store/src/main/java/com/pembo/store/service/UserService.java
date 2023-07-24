package com.pembo.store.service;

import com.pembo.store.dto.UserDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.UserMapper;
import com.pembo.store.model.User;
import com.pembo.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(userMapper::toDto)
                             .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.toDto(user.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not " +
                                                                                             "found")));
    }

    public UserDto saveUser(UserDto userDto) {
        User newUser = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not " +
                                                                                           "found"));
        userMapper.partialUpdate(userDto, user);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
