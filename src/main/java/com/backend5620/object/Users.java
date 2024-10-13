package com.backend5620.object;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {

    private int id; // ID
    private String name; // Name
    private String email; // Email
    private String phone; // Phone

    private int accessType; // AccessType
    private String username; // Username
    private String password; // Password (default is '123456')

}

