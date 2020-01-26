package cn.com.gmall.service.impl;

import cn.com.gmall.bean.UmsMember;
import cn.com.gmall.mapper.MemberMapper;
import cn.com.gmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<UmsMember> getMembers() {
        return memberMapper.selectAll();
    }

}
