package org.zhaoyangli.ravenote.exception;

// a child class of the RuntimeException for extracting the custom error message
public class CustomException extends RuntimeException {

    private int code;
    private String message;

    public CustomException(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(CustomErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
