package com.jean.car.dto;

import lombok.Data;

@Data
//En esta clase mandamos los parametros necesarios para acceder a la cuenta
public class SignupRequest {

    private String email;
    private String name;
    private String password;
}
