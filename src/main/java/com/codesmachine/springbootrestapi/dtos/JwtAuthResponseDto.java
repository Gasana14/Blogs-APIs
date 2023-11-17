package com.codesmachine.springbootrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";

}
