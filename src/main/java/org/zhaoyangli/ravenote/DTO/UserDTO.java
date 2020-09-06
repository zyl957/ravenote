package org.zhaoyangli.ravenote.DTO;

import lombok.Data;
import org.zhaoyangli.ravenote.model.Unit;

import java.util.Collection;

@Data
public class UserDTO {

    private int id;

    private String username;

    private String position;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private String course;

    private String level;

    private Unit[] units;

    private int[] collection;
}
