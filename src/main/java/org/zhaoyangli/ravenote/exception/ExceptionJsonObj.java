package org.zhaoyangli.ravenote.exception;

import lombok.Data;

// to package a object which implemented CustomErrorCode into a JSON object
@Data
public class ExceptionJsonObj {

    int code;
    String message;

    public ExceptionJsonObj(CustomErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
