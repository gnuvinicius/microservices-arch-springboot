package com.garage.auth.infraestructure.api.auth.dtos;

public record RequestRefreshPasswordDto(String email, String tokenRefreshPassword, String newPassword) {
}