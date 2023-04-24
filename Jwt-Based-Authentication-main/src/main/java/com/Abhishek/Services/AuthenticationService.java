package com.Abhishek.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Abhishek.Models.AuthenticationRequest;
import com.Abhishek.Models.AuthenticationResponse;
import com.Abhishek.Models.RegisterRequest;
import com.Abhishek.Models.Role;
import com.Abhishek.Models.User;
import com.Abhishek.Repos.UserRepo;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        User user=new User();
        user.setFname(request.getFname());
        user.setLname(request.getLname());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        repo.save(user);

        String token=service.genrateToken(user);

       
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user =repo.findByEmail(request.getEmail()).orElseThrow();

        var token =service.genrateToken(user);
        
       return new AuthenticationResponse(token);
    }


    
}
