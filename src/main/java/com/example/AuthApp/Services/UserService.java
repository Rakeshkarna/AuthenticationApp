package com.example.AuthApp.Services;

import com.example.AuthApp.Models.Users;
import com.example.AuthApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users getUserById(Long userId){
      return userRepository.findByUserId(userId);
    }

    public void saveUser(Users user){
        userRepository.save(user);
    }

    public Users getUserByEmail(String email){
        return userRepository.findUsersByEmail(email);
    }

    public ResponseEntity<Object> updateExistingProfile(Users newDetails, Long id){
        Users existingDetails = userRepository.findByUserId(id);

        if(!Objects.isNull(newDetails.getEmail())){
            if(Objects.nonNull(userRepository.findUsersByEmail(newDetails.getEmail())))
                return new ResponseEntity<>("Email Already Exists",HttpStatus.CONFLICT);

            existingDetails.setEmail(newDetails.getEmail());
        }
        if(!Objects.isNull(newDetails.getPassword()))existingDetails.setPassword(newDetails.getPassword());
        if(!Objects.isNull(newDetails.getUsername())){
            if(Objects.nonNull(userRepository.findUsersByUsername(newDetails.getUsername())))
                return new ResponseEntity<>("Username Already Exists,Please try Different Username",HttpStatus.CONFLICT);
            existingDetails.setUsername(newDetails.getUsername());
        }
        userRepository.save(existingDetails);

        return new ResponseEntity<>("Profile Updated Succesfully",HttpStatus.ACCEPTED);
    }



}
