package com.kbm.instagram.controllerImpl;

import com.kbm.instagram.controller.TestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class TestControllerImpl implements TestController {

    @GetMapping("/getMsg")
    @ResponseBody
    public String getMsg() {
        return "test success!!";
    }
}
