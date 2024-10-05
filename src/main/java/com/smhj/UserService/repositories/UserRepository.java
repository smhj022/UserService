package com.smhj.UserService.repositories;

import com.smhj.UserService.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user); //upsert

    Optional<User> findByEmail(String email);


}
