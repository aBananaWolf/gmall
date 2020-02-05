package cn.com.gmall.service.manage;

import cn.com.gmall.beans.PmsSkuInfo;
import cn.com.gmall.beans.PmsSkuSaleAttrValue;

import java.util.List;

public interface SkuService {

    void saveSkuInfo(PmsSkuInfo skuInfo);

    PmsSkuInfo getSkuInfo(Integer skuId);

    List<PmsSkuInfo> getSaleAttrValueByHash(Long spuId);
}
