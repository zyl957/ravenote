package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class Unit {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the formal id of this unit, e.g. "CM50109"
    private String unitId;

    // the title of this unit
    private String unitName;

}
