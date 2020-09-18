package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class User {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the username of this user
    private String username;

    // the identity of this user, e.g. "student" "teacher"
    private String position;

    private String firstName;

    private String lastName;

    private String email;

    //e.g. "Computer Science Department"
    private String department;

    private String course;

    // e.g. "Bachelor" "Master"
    private String level;

    public User(String username) {
        this.username = username;
    }
}
