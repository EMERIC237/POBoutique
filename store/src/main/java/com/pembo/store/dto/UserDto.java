package com.pembo.store.dto;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.pembo.store.model.User}
 */

public record UserDto(Long id,
                      @Size(max = 50) @NotEmpty @NotBlank(message = "Username cannot be empty") String username,
                      @NotNull @Size(message = "incorrect password length", min = 6, max = 255) @NotEmpty(message = "Password cannot be empty") @NotBlank String password,
                      @NotNull @Size(max = 255) @Email(message = "Incorrect email") @NotBlank(message = "Email cannot be empty") String email,
                      @NotNull @Size(max = 255) String role,
                      @NotNull @Size(max = 255) @NotBlank(message = "first name cannot be empty") String firstName,
                      @NotNull @Size(max = 255) String lastName,
                      @Size(message = "incorrect number", min = 8, max = 255) String phoneNumber) implements Serializable {
}