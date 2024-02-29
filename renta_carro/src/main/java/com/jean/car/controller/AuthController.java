package com.jean.car.controller;

import com.jean.car.dto.SignupRequest;
import com.jean.car.dto.UserDto;
import com.jean.car.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;


    //Metodo para registrarse, mandamos el cuerpo con
    @PostMapping("/signup")
    public ResponseEntity<?> signupCliente(@RequestBody SignupRequest signupRequest){

        if(authService.hasClienteWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Cliente con el email ya existe",HttpStatus.NOT_ACCEPTABLE);

        UserDto createdClienteDto= authService.createCliente(signupRequest);

        if(createdClienteDto==null){
            return new ResponseEntity<>("Cliente no creado, intente de nuevo", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdClienteDto,HttpStatus.CREATED);
    }
}
