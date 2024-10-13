package com.backend5620.controller;


import com.backend5620.object.Result;
import com.backend5620.object.Users;
import com.backend5620.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class RegisterControlled {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody Users user) {
        boolean isRegistered = userService.register(user);
        if (isRegistered) {
            return Result.success("Registration successful");

        } else {
            return Result.error("Registration failed, user already exists");
        }
    }

}
