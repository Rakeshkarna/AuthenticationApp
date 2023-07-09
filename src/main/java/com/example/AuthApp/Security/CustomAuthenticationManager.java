package com.example.AuthApp.Security;

import com.example.AuthApp.Models.Users;
import com.example.AuthApp.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomAuthenticationManager  implements AuthenticationManager {


    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException ,NullPointerException{
        //System.out.println(authentication.getName().toString());
        //Users user = userRepository.findUsersByUsername(authentication.getName().toString());
       Users user = userService.getUserByUsername(authentication.getName().toString());
       System.out.println(user.getPassword());
        System.out.println(authentication.getCredentials());
        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(),user.getPassword())){
            throw new BadCredentialsException("Wrong Password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(),authentication.getCredentials());
    }

}
