package com.jean.car.services.auth;

import com.jean.car.dto.SignupRequest;
import com.jean.car.dto.UserDto;
import com.jean.car.entity.User;
import com.jean.car.enums.UserRole;
import com.jean.car.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    //Inyectamos UserRepository para que traige todos los metodos crud
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createCliente(SignupRequest signupRequest) {
        //Creamos un cliente
        User user = new User();
        //Seteamos los valores del usuario
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.CLIENTE);
        //Usuario creado lo guardamos con userRepository
        User createdUser= userRepository.save(user);

        UserDto userDto=new UserDto();
        //Seteamos el id del usuario creado al dto para tener los valores del usuario
        userDto.setId(createdUser.getId());
        return userDto;
    }

    //Este metodo es para que no se repitan usuarios con el mismo email
    @Override
    public boolean hasClienteWithEmail(String email) {
        return false;
    }
}
