package com.kbm.instagram.controllerImpl;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class TestControllerImpl {

    @GetMapping("/getMsg")
    @ResponseBody
    public String getMsg() {
        return "test success!!";
    }
}
