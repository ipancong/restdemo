package com.example.demo.controller;

import com.example.demo.I18nService;
import com.example.demo.common.ErrorCode;
import com.example.demo.exception.SumException;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private I18nService i18nService;

    @GetMapping("/hello")
    public String test01() {
        return "hello this is test success";
    }

    @GetMapping("/hello/fail")
    public String test02() {
        throw new SumException(ErrorCode.SAVE_ERROR);
    }

    @GetMapping("/users")
    public List<UserInfo> test03() {
        UserInfo user3 = new UserInfo("pancong", "123", 25);
        UserInfo user2 = new UserInfo("xxxx", "122223", 24);
        UserInfo user1 = new UserInfo("llll", "122223", 28);
        List<UserInfo> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

}
