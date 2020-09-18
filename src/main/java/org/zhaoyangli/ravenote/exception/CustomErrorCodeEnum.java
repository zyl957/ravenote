package org.zhaoyangli.ravenote.exception;

// the enumeration of all custom error codes and corresponding messages
// one of the implementation classes of the interface CustomErrorCode
public enum CustomErrorCodeEnum implements CustomErrorCode{

    OPERATION_SUCCESS(1000,"Success!"),
    UNKNOWN_ERROR(1001,"Sorry, we don't know what has happened. Maybe come back later?"),
    HAVE_NO_ACCESS(1002,"Sorry, you don't have the access to this resource. Maybe try something else?"),

    WRONG_USERNAME_OR_PASSWORD(2001,"Sorry, your input is incorrect. Try again?"),
    DUPLICATE_USERNAME(2002,"Sorry, this username is taken. Try another one?"),
    INVALID_USERNAME_LENGTH(2003,"Sorry, the length of the username should be between 5 and 10."),
    INVALID_CHARACTERS(2004,"Sorry, It contains invalid characters. Please replace all of them."),
    INVALID_PASSWORD_LENGTH(2005,"Sorry, the length of the password should be between 6 and 16."),

    USER_NOT_FOUND(3001,"Sorry, we can't find this user. Maybe visit some others?"),
    UNIT_NOT_FOUND(3002,"Sorry, we can't find this unit. Maybe visit some others?"),
    LECTURE_NOT_FOUND(3003,"Sorry, we can't find this lecture. Maybe visit some others?"),
    PAGE_NOT_FOUND(3004,"Sorry, we can't find this page. Maybe visit some others?"),
    NOTE_NOT_FOUND(3005,"Sorry, we can't find this note. Maybe visit some others?")

    ;

    private int code;
    private String message;

    CustomErrorCodeEnum(int code,String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
