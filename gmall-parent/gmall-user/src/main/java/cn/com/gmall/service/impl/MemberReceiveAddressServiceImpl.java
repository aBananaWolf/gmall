package cn.com.gmall.service.impl;

import cn.com.gmall.beans.UmsMemberReceiveAddress;
import cn.com.gmall.mapper.MemberReceiveAddressMapper;
import cn.com.gmall.service.MemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MemberReceiveAddressServiceImpl implements MemberReceiveAddressService {
    @Autowired
    private MemberReceiveAddressMapper addressMapper;

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressById(Long id) {
        Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId",id);
        return addressMapper.selectByExample(example);
    }
}
