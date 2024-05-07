package com.capgemini.wsb.fitnesstracker.user.internal.dto;

import jakarta.annotation.Nullable;

/*
    DTO for basic information about user.
 */
public record BasicUserDto(@Nullable Long id, String name) {
}
