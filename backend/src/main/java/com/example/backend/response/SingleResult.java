package com.example.backend.response;

import lombok.Data;

@Data
public class SingleResult<T> extends CommonResult {
    private T data;

}
