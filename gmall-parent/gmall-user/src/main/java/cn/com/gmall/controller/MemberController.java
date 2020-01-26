package cn.com.gmall.controller;

import cn.com.gmall.beans.UmsMember;
import cn.com.gmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/getMembers")
    public List<UmsMember> getMembers(){
        return memberService.getMembers();
    }
}
