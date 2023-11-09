package com.security.spring.controller;

import com.security.spring.user.IUserRepository;
import com.security.spring.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class GenericController {
    @Autowired
    private IUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String teste(){
        return "<h1>Teste</h1>";
    }

    @GetMapping("/livre")
    public String livre(){
        return "<h1>Acesso livre</h1>";
    }

    @PostMapping("/user/create")
    public ResponseEntity<UserModel> create(@RequestBody UserModel model){
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return new ResponseEntity<>(this.repository.save(model), HttpStatus.CREATED);
    }
}
