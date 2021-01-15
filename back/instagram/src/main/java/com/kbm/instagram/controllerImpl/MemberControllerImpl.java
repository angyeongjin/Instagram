package com.kbm.instagram.controllerImpl;

import com.kbm.instagram.controller.MemberController;
import com.kbm.instagram.dto.MemberDto;
import com.kbm.instagram.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class MemberControllerImpl implements MemberController {

    @Autowired
    public MemberService memberService;

    @GetMapping("/{id}")
    @ResponseBody
    public MemberDto getMemberInfo(@PathVariable("id") long id) {
        return memberService.getMemberInfo(id);
    }
}
