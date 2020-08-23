package com.thoughtworks.quiz.common.errors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    BASIC_DATA_ERROR(4001, "数据出错了"),
    BASIC_PARAMS_ERROR(4002, "参数出错了"),
    ORDER_NO_Data_Error(4003, "获取订单数据出错");



    private final int code;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
