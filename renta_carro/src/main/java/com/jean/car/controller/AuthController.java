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
        //Si el cliente ya existe con ese email le manda error
        if(authService.hasClienteWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Cliente con el email ya existe",HttpStatus.NOT_ACCEPTABLE);

        //Traemos los parametros mediante el dto para poder crear el cliente, con el parametro de signupRequest
        //para traer los requisitos para crear al cliente
        UserDto createdClienteDto= authService.createCliente(signupRequest);

        //Si los espacios viene alguno vacio el cliente no se va crear, en todo caso cumpla los parametros
        //retorna que el cliente creado
        if(createdClienteDto==null){
            return new ResponseEntity<>("Cliente no creado, intente de nuevo", HttpStatus.BAD_REQUEST);
        }
        //Retorna el cliente creado
        return new ResponseEntity<>(createdClienteDto,HttpStatus.CREATED);
    }
}
