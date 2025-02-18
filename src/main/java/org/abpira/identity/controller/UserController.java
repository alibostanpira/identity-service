package org.abpira.identity.controller;

import lombok.RequiredArgsConstructor;
import org.abpira.identity.dto.UserRequestDTO;
import org.abpira.identity.dto.UserResponseDTO;
import org.abpira.identity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkUserExists(Long id) {
        return ResponseEntity.ok(userService.checkUserExists(id));
    }
}
