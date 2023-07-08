package com.example.AuthApp.Repository;

import com.example.AuthApp.Models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users,Long> {
    @Override
    Optional<Users> findById(Long userId);

    public Users findByUserId(Long userId);
    Users findUsersByEmail(String email);

    Users findUsersByUsername(String username);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Users> S save(S entity);
}
