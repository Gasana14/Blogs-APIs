package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.dtos.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/documentation")
public class APIsDocumentationController {

    @GetMapping("")
    public String documentation(){
     return "redirect:/swagger-ui/index.html";
    }
}
