package cn.com.gmall.service.impl;

import cn.com.gmall.anno.Service;
import cn.com.gmall.beans.PmsSkuAttrValue;
import cn.com.gmall.beans.PmsSkuImage;
import cn.com.gmall.beans.PmsSkuInfo;
import cn.com.gmall.beans.PmsSkuSaleAttrValue;
import cn.com.gmall.mapper.SkuAttrValueMapper;
import cn.com.gmall.mapper.SkuImageMapper;
import cn.com.gmall.mapper.SkuInfoMapper;
import cn.com.gmall.mapper.SkuSaleAttrValueMapper;
import cn.com.gmall.service.manage.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuInfoMapper infoMapper;
    @Autowired
    private SkuAttrValueMapper attrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper saleAttrValueMapper;
    @Autowired
    private SkuImageMapper imageMapper;


    @Override
    public void saveSkuInfo(PmsSkuInfo skuInfo) {
        infoMapper.insert(skuInfo);
        for (PmsSkuAttrValue pmsSkuAttrValue : skuInfo.getSkuAttrValueList()) {
            pmsSkuAttrValue.setSkuId(skuInfo.getId());
            attrValueMapper.insert(pmsSkuAttrValue);
        }
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuInfo.getSkuSaleAttrValueList()) {
            pmsSkuSaleAttrValue.setSkuId(skuInfo.getId());
            saleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }
        for (PmsSkuImage pmsSkuImage : skuInfo.getSkuImageList()) {
            pmsSkuImage.setSkuId(skuInfo.getId());
            imageMapper.insert(pmsSkuImage);
        }
    }

    @Override
    public PmsSkuInfo getSkuInfo(Integer skuId) {
        Example example = new Example(PmsSkuInfo.class);
        example.createCriteria().andEqualTo("id",skuId);
        PmsSkuInfo skuInfo = infoMapper.selectOneByExample(example);
        Example imgExample = new Example(PmsSkuImage.class);
        imgExample.createCriteria().andEqualTo("skuId",skuId);
        List<PmsSkuImage> pmsSkuImages = imageMapper.selectByExample(imgExample);
        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSaleAttrValueByHash(Long spuId) {
        return infoMapper.selectSaleAttrValueByHash(spuId);
    }
}
