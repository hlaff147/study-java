package com.studycode.springcode.controller;

import com.studycode.springcode.dto.UserDTO;
import com.studycode.springcode.entity.User;
import com.studycode.springcode.service.UserService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isEmpty()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userOptional.get();
        UserDTO dto = userService.parseToDTO(user);
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserDTO> dto = userList.stream().map(userService::parseToDTO).toList();
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus>  deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
