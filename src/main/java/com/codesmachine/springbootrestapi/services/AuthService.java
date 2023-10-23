package com.codesmachine.springbootrestapi.services;

import com.codesmachine.springbootrestapi.dtos.LoginDto;
import com.codesmachine.springbootrestapi.dtos.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
