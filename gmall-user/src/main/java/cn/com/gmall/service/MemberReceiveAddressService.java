package cn.com.gmall.service;

import cn.com.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface MemberReceiveAddressService {
    public List<UmsMemberReceiveAddress> getReceiveAddressById(Long id);
}
