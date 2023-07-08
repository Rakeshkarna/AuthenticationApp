package com.example.AuthApp.Controllers;

import com.example.AuthApp.Models.Users;
import com.example.AuthApp.Services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.OnClose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable Long id){
        Users user = userService.getUserById(id);
        if(Objects.isNull(id)){
            return new ResponseEntity<>("Invalid User Id or User Id doesn't exist",HttpStatus.BAD_REQUEST );
        }
        Users responseUser = Users.
                builder().
                username(user.getUsername()).
                userId(user.getUserId()).
                email(user.getEmail()).
                password("*****")
                .build();
        return new ResponseEntity<>(responseUser, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<Object>registerUser(@RequestBody @Valid Users user){
        if(Objects.isNull(user)){
            return  new ResponseEntity<>("Invalid Details", HttpStatus.BAD_REQUEST);
        }
        if(userService.getUserByEmail(user.getEmail()) !=null){
            return new ResponseEntity<>("Email already Exist",HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);
       return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }


    @PutMapping("/profile/{id}")
    public ResponseEntity<Object>updateProfile(@PathVariable Long id, @RequestBody Users user){
        if(Objects.isNull(user) || (Objects.isNull(user.getPassword()) && Objects.isNull(user.getUsername()) && Objects.isNull(user.getEmail()))){
            return new ResponseEntity<>("Please provide Update details", HttpStatus.BAD_REQUEST);
        }

        return userService.updateExistingProfile(user ,id);
    }







}
