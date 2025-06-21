package com.nicoarbio.cardealership.customer.dto;

import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotBlank @Size(min = 1, max = 30) String firstName,
        @NotBlank @Size(min = 1, max = 30) String lastName,
        @NotBlank @Size(min = 6, max = 10) String dni,
        @NotBlank @Size(min = 6, max = 35) @Email String email,
        @NotBlank @Size(min = 1, max = 20) String phone,
        @NotBlank @Size(min = 1, max = 255) String address
) {}
