package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class User {

    private int id;

    private String username;

    private String position;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private String course;

    private String level;

    public User(String username) {
        this.username = username;
    }
}
