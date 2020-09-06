package org.zhaoyangli.ravenote.exception;

import lombok.Data;

/**
 * Exception handling object especially for JSON transport
 */

@Data
public class ExceptionJsonObj {

    int code;
    String message;

    public ExceptionJsonObj(CustomErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
