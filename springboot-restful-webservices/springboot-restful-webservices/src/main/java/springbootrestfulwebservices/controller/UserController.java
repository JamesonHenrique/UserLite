package springbootrestfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootrestfulwebservices.entity.User;
import springbootrestfulwebservices.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser,
                HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
       User user = userService.getUserById(userId);
        return new ResponseEntity<>(user,
                HttpStatus.OK);
    }
}
