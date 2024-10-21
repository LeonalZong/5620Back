package com.backend5620.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {

    private int userId;
    private String username;
    private String password; // 密码（默认 '123456'）
    private String email;
    private String role; // 用户角色 (例如 'Customer', 'Expert', 'TechTeam')
    private String registrationDate; // 注册时间，格式为 TIMESTAMP

    public int getId() {
        return userId;
    }
}
