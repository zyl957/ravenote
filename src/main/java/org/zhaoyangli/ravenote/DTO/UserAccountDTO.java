package org.zhaoyangli.ravenote.DTO;

import lombok.Data;
import org.zhaoyangli.ravenote.model.Unit;

@Data
public class UserAccountDTO {

    private int id;

    private String username;

    // all of the units this user account is taking
    private Unit[] units;

    // all of the ids of the collection information of this user account
    private int[] collection;

}
