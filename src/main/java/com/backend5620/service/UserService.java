package com.backend5620.service;

import com.backend5620.object.Users;

public interface UserService {
    Users login(Users user);

    boolean register(Users user);
}
