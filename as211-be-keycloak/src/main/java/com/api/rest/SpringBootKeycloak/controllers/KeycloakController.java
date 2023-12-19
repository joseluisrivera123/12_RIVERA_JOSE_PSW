package com.api.rest.SpringBootKeycloak.controllers;

import com.api.rest.SpringBootKeycloak.dto.ResponseMessage;
import com.api.rest.SpringBootKeycloak.dto.UserDTO;
import com.api.rest.SpringBootKeycloak.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/keycloak/user")
public class KeycloakController {

    @Autowired
    private IKeycloakService keycloakService;


    @GetMapping("/search")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }


    @GetMapping("/search/{username}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String username){
         return ResponseEntity.ok(keycloakService.searchUserByUsername(username));
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        ResponseMessage response = keycloakService.createUser(userDTO);
        return ResponseEntity.status(201).body(response);
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO){
        keycloakService.updateUser(userId, userDTO);
        return ResponseEntity.ok("User updated successfully");
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
