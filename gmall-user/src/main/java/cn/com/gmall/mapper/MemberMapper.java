package cn.com.gmall.mapper;

import cn.com.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MemberMapper extends Mapper<UmsMember> {
    List<UmsMember> selectMembers();
}
