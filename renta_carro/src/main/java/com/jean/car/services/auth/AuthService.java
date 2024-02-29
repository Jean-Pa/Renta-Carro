package com.jean.car.services.auth;

import com.jean.car.dto.SignupRequest;
import com.jean.car.dto.UserDto;

public interface AuthService {

    //Este Dto va traer las credenciales para que el usuario pueda
    //ingresar su cuenta, es como una solicitud
    UserDto createCliente(SignupRequest signupRequest);

    boolean hasClienteWithEmail(String email);
}
