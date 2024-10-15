package com.backend5620.controller;


import com.backend5620.object.Result;
import com.backend5620.object.Users;
import com.backend5620.pojo.Jwtutils;
import com.backend5620.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Users user) {
        log.info("Login user: {}", user);
        Users u = userService.login(user);

        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getUserId());

            claims.put("username", u.getUsername());

            String jwt = Jwtutils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("Username or password may wrong");
    }




}
