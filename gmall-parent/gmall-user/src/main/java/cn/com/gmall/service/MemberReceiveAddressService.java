package cn.com.gmall.service;

import cn.com.gmall.beans.UmsMemberReceiveAddress;

import java.util.List;

public interface MemberReceiveAddressService {
    List<UmsMemberReceiveAddress> getReceiveAddressById(Long id);
}
