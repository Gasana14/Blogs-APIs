package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.dtos.JwtAuthResponseDto;
import com.codesmachine.springbootrestapi.dtos.LoginDto;
import com.codesmachine.springbootrestapi.dtos.RegisterDto;
import com.codesmachine.springbootrestapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


//     Build Login REST API Without JWT
//    @PostMapping(value = {"/login","/signin"})
//    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
//       String response = authService.login(loginDto);
//        return ResponseEntity.ok(response);
//    }

    // Build Login REST API With JWT
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponseDto);
    }

    // Build Register REST API
    @PostMapping(value = {"/register","signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
