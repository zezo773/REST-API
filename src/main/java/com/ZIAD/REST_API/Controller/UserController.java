package com.ZIAD.REST_API.Controller;

import com.ZIAD.REST_API.DTO.UserDto;
import com.ZIAD.REST_API.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    Build Create User REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userdto = userService.getUserById(id);
        return ResponseEntity.ok(userdto);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok(userDtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> UpdateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto updateUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

}
