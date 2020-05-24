package com.xatu.onlineedu.exception;

public class EduException extends RuntimeException {
    private  String message;
    private  Integer code;

    public EduException(String message) {
        this.message = message;
    }
    public EduException(Integer code,String message) {
        this.message = message;
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
