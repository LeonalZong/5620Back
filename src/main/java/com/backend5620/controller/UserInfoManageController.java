package com.backend5620.controller;

import com.backend5620.object.Result;
import com.backend5620.object.Users;
import com.backend5620.pojo.Jwtutils;
import com.backend5620.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoManageController {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Result getUserInfo(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");
            Users user = userService.getUserById(userId);
            return Result.success(user);
        } catch (Exception e) {
            log.error("Failed to get user info", e);
            return Result.error("Failed to get user info: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody Users updateUser, @RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");

            log.info("Token userId: {}", userId);
            log.info("Update request userId: {}", updateUser.getUserId());

            // 验证是否是当前用户的信息
            if (updateUser.getUserId() != userId) {
                log.warn("User ID mismatch - Token ID: {}, Request ID: {}", userId, updateUser.getUserId());
                return Result.error("Unauthorized to update this user's information");
            }

            // 检查用户是否存在
            Users existingUser = userService.getUserById(userId);
            if (existingUser == null) {
                log.warn("User not found with ID: {}", userId);
                return Result.error("User not found");
            }

            // 检查邮箱是否已被其他用户使用
            Users userWithEmail = userService.getUserByEmail(updateUser.getEmail());
            if (userWithEmail != null && userWithEmail.getUserId() != userId) {
                log.warn("Email {} already in use by user {}", updateUser.getEmail(), userWithEmail.getUserId());
                return Result.error("Email already in use by another user");
            }

            log.info("Updating user info for userId: {}", userId);
            userService.updateUserInfo(updateUser);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to update user info", e);
            return Result.error("Failed to update user info: " + e.getMessage());
        }
    }
}
