package it.jac.spring.secondaprova.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;
    
}