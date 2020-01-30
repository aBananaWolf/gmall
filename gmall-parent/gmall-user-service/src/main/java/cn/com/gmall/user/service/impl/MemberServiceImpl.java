package cn.com.gmall.user.service.impl;

import cn.com.gmall.beans.UmsMember;
import cn.com.gmall.user.mapper.MemberMapper;
import cn.com.gmall.service.user.MemberService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
