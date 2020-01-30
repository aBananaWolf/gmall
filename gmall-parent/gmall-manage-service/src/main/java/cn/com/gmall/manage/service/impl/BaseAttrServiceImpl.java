package cn.com.gmall.manage.service.impl;

import cn.com.gmall.beans.PmsBaseAttrInfo;
import cn.com.gmall.beans.PmsBaseAttrValue;
import cn.com.gmall.manage.mapper.BaseAttrInfoMapper;
import cn.com.gmall.manage.mapper.BaseAttrValueMapper;
import cn.com.gmall.service.manage.BaseAttrService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class BaseAttrServiceImpl implements BaseAttrService {
    @Autowired
    private BaseAttrInfoMapper infoMapper;
    @Autowired
    private BaseAttrValueMapper valueMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(Integer catalog3Id) {
        Example example = new Example(PmsBaseAttrInfo.class);
        example.createCriteria().andEqualTo("catalog3Id", catalog3Id);
        return infoMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdateAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        Long id = pmsBaseAttrInfo.getId();
        if (StringUtils.isEmpty(id)) {
            this.addAttrInfo(pmsBaseAttrInfo);
        } else {
            Example infoExample = new Example(PmsBaseAttrInfo.class);
            infoExample.createCriteria().andEqualTo("id", id);
            infoMapper.deleteByExample(infoExample);

            Example valueExample = new Example(PmsBaseAttrValue.class);
            valueExample.createCriteria().andEqualTo("attrId", id);
            valueMapper.deleteByExample(valueExample);
            this.addAttrInfo(pmsBaseAttrInfo);
        }

    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(Integer attrId) {
        Example example = new Example(PmsBaseAttrValue.class);
        example.createCriteria().andEqualTo("attrId", attrId);
        return valueMapper.selectByExample(example);
    }

    private void addAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        infoMapper.insertSelective(pmsBaseAttrInfo);
        for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
            pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
            valueMapper.insertSelective(pmsBaseAttrValue);
        }
    }

   /* @Override
    public List<PmsBaseAttrValue> attrInfoList(Integer catalog3Id) {
        Example example = new Example(PmsBaseAttrInfo.class);
        Example.Criteria criteria = example.createCriteria();
        return infoMapper.selectByExample(criteria);
    }*/
}
