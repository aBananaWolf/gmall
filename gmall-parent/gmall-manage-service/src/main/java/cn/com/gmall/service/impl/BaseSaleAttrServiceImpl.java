package cn.com.gmall.service.impl;

import cn.com.gmall.anno.Service;
import cn.com.gmall.beans.PmsBaseSaleAttr;
import cn.com.gmall.mapper.BaseSaleAttrMapper;
import cn.com.gmall.service.manage.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BaseSaleAttrServiceImpl implements BaseSaleAttrService {
    @Autowired
    private BaseSaleAttrMapper saleAttrMapper;

    @Override
    public List<PmsBaseSaleAttr> getBaseSaleAttrList() {
        return saleAttrMapper.selectAll();
    }

}
