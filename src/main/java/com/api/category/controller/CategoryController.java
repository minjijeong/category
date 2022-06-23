package com.api.category.controller;

import com.api.category.model.message.CommonResponseMessage;
import com.api.category.model.response.CommonResponse;
import com.api.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping("/hello")
    public CommonResponse sayHello(){
        CommonResponse result = new CommonResponse();
        result.setSuccess(true);
        result.setCode(CommonResponseMessage.SUCCESS.getCode());
        result.setMsg(CommonResponseMessage.SUCCESS.getMsg());
        return result;
    }

}
