package com.zygh.lz.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    //暂时先注释 如果放开报错请安装idea的lombok插件
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
