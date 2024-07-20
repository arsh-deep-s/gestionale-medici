package it.jac.spring.secondaprova.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;

    // I metodi getter sono generati automaticamente da @Data

    // Aggiungi i metodi setter manuali per il concatenamento
    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}
