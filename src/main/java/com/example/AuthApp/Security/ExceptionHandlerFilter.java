package com.example.AuthApp.Security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
           filterChain.doFilter(request,response);
        }catch (EntityNotFoundException ex){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Username doesn't exist");
            response.getWriter().flush();

        }catch (JWTVerificationException ex){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("JWT not VALID");
            response.getWriter().flush();

        }
        catch(RuntimeException ex){
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();


        }
    }
}
