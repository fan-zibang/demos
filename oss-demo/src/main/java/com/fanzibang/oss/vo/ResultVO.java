package com.fanzibang.oss.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局响应封装类
 *
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public ResultVO(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResultVO(int code, T data) {
        this(code, data, "");
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(0, data, "success");
    }

    public static <T> ResultVO<T> error() {
        return new ResultVO<>(-1, null, "error");
    }

}
