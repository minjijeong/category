package com.api.category.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
    //응답 성공여부 : true/false
    private boolean success;

    //응답 코드 번호 : > 0 정상, < 0 비정상
    private int code;

    //응답 메시지
    private String msg;

}
