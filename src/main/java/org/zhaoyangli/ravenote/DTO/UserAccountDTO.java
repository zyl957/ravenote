package org.zhaoyangli.ravenote.DTO;

import lombok.Data;
import org.zhaoyangli.ravenote.model.Unit;

@Data
public class UserAccountDTO {

    private int id;

    private String username;

    private Unit[] units;

    private int[] collection;

}
