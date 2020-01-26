package cn.com.gmall.controller;

import cn.com.gmall.bean.UmsMemberReceiveAddress;
import cn.com.gmall.service.MemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberReceiveAddressController {
    @Autowired
    private MemberReceiveAddressService addressService;

    @GetMapping("/getReceiveAddressById/{id}")
    public List<UmsMemberReceiveAddress> getMemberReceiveAddressById(@PathVariable("id")Long id) {
        return addressService.getReceiveAddressById(id);
    }

}
