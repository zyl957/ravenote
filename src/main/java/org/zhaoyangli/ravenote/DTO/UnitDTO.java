package org.zhaoyangli.ravenote.DTO;

import lombok.Data;
import org.zhaoyangli.ravenote.model.Lecture;

@Data
public class UnitDTO {

    private int id;

    private String unitId;

    private String unitName;

    private Lecture[] lectures;

}
