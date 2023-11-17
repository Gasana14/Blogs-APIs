package com.codesmachine.springbootrestapi.services.impl;

import com.codesmachine.springbootrestapi.domain.Role;
import com.codesmachine.springbootrestapi.domain.User;
import com.codesmachine.springbootrestapi.dtos.LoginDto;
import com.codesmachine.springbootrestapi.dtos.RegisterDto;
import com.codesmachine.springbootrestapi.exceptions.BlogAPIsException;
import com.codesmachine.springbootrestapi.repositories.RoleRepository;
import com.codesmachine.springbootrestapi.repositories.UserRepository;
import com.codesmachine.springbootrestapi.security.JwtTokenProvider;
import com.codesmachine.springbootrestapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;





//    Login without using jwt
//    @Override
//    public String login(LoginDto loginDto) {
//         Authentication authentication =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
//
//        // store our authentication into Security Context
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return "User Logged in successfully";
//    }


//  Login with JWT Token
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));

        // store our authentication into Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateJwtToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // check if username exist in the database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIsException(HttpStatus.BAD_REQUEST,"Username is already exist");
        }

        // check if email exist in the database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIsException(HttpStatus.BAD_REQUEST,"Email is already exist");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // assign roles
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully";
    }
}
