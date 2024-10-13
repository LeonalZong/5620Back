package com.backend5620.service.impl;



import com.backend5620.mapper.UserMapper;
import com.backend5620.object.Users;
import com.backend5620.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Users login(Users user) {

        Users u = userMapper.getByUsernameAndPassword(user);
        return u;
    }

    public boolean register(Users user) {
        Users existUser = userMapper.getByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword("123456");

        }

        userMapper.insertUser(user);
        return true;
    }
}
