package com.backend5620.service;

import com.backend5620.object.Users;

public interface UserService {
    Users login(Users user);

    boolean register(Users user);

    void updateUserInfo(Users user);
    Users getUserById(int userId);
    Users getUserByEmail(String email);
}
