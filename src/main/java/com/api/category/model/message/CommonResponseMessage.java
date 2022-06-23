package com.api.category.model.message;

public enum CommonResponseMessage {
    SUCCESS(0, "성공하였습니다.");

    int code;
    String msg;

    CommonResponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
