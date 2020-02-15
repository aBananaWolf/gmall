package cn.com.gmall.manage.service.manage;

import cn.com.gmall.beans.ItemSaleHashPackage;
import cn.com.gmall.beans.PmsSkuInfo;

import java.util.List;

public interface SkuService {

    void saveSkuInfo(PmsSkuInfo skuInfo);

    /**
     * skuInfo 和 skuImg,销售属性以及默认选中
     * @param skuId
     * @return
     */
    PmsSkuInfo getSkuInfo(Long skuId,boolean skuImg,boolean getSpuSaleAttrListCheckBySku);

    /**
     * 封装 int level HashMap<String, String> skuHashMap, LinkedHashSet<String> firstLineImg
     *
     * @param spuId
     * @return
     */
    ItemSaleHashPackage getItemSaleHashPackageBySpuId(Long spuId);

    List<PmsSkuInfo> getAllSkuInfo();
}
