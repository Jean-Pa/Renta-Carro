package com.jean.car.repository;

import com.jean.car.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Un optional del usuario para buscar su email
    Optional<User> findFirstByEmail(String email);
}
