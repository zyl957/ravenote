package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserAccount {

    //auto increment id for database
    private int id;

    private String username;

    private String password;

    //for cookie
    private String token;

    // the GMT timestamp when the account was created
    private Date gmtCreate;

    // the GMT timestamp when the account was last modified
    private Date gmtModified;

    public UserAccount() {
    }

    public UserAccount(String username, String password) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.token = null;
        this.gmtCreate = null;
        this.gmtModified = null;
    }

    public UserAccount(String username, String password, String token, Date gmtCreate, Date gmtModified) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

}
