package com.kbm.instagram.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class TestController {

    @GetMapping("/getMsg")
    @ResponseBody
    public String getMsg() {
        return "test success!!";
    }
}
