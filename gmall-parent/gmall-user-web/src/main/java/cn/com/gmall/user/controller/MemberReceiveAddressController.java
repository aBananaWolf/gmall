package cn.com.gmall.user.controller;

import cn.com.gmall.beans.UmsMemberReceiveAddress;
import cn.com.gmall.manage.service.user.MemberReceiveAddressService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberReceiveAddressController {
//    @Reference
    private MemberReceiveAddressService addressService;

    @GetMapping("/getReceiveAddressById/{id}")
    public List<UmsMemberReceiveAddress> getMemberReceiveAddressById(@PathVariable("id")Long id) {
        return addressService.getReceiveAddressById(id);
    }

}
