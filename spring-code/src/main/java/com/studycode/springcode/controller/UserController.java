package com.studycode.springcode.controller;

import com.studycode.springcode.dto.UserDTO;
import com.studycode.springcode.entity.User;
import com.studycode.springcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        User user = userService.save(dto);
        UserDTO userDTO = userService.parseToDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("GET MAPPING");
    }
}
