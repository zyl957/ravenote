package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

// the DTO for changing the visibility of notes
@Data
public class HideShowDTO {

    private int noteId;

    // 0: private 1: public 2: protected (in the future release)
    private int visibility;
}
