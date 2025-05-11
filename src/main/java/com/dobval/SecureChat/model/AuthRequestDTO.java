package com.dobval.SecureChat.model;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
}
