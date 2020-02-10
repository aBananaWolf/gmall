package cn.com.gmall.item.controller;

import cn.com.gmall.beans.ItemSaleHashPackage;
import cn.com.gmall.beans.PmsProductSaleAttr;
import cn.com.gmall.beans.PmsSkuInfo;
import cn.com.gmall.beans.PmsSkuSaleAttrValue;
import cn.com.gmall.manage.service.manage.SkuService;
import cn.com.gmall.manage.service.manage.SpuService;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class ItemController {
    @Reference
    private SkuService skuService;

    @RequestMapping("/{skuId}.html")
    public String toItemPage(@PathVariable Long skuId,ModelMap modelMap){
        // 查询出sku基本信息和图片信息 并查询出销售列表和默认选中属性， 处理缓存
        PmsSkuInfo skuInfo = skuService.getSkuInfo(skuId,true,true);
        // 销售属性列表
        List<PmsProductSaleAttr> spuSaleAttrListCheckBySku = skuInfo.getSpuSaleAttrListCheckBySku();
        skuInfo.setSpuSaleAttrListCheckBySku(null);
        // 获取页面上所需的一些属性
        ItemSaleHashPackage itemSaleHashPackage = skuService.getItemSaleHashPackageBySpuId(skuInfo.getSpuId());
        // 图片和sku·信息
        modelMap.addAttribute("skuInfo",skuInfo);
        PmsProductSaleAttr pmsProductSaleAttr = spuSaleAttrListCheckBySku.get(0);
        // 第一行销售属性需要额外处理
        modelMap.addAttribute("spuSaleAttrListCheckBySku2",pmsProductSaleAttr);
        spuSaleAttrListCheckBySku.remove(0);
        // 第二行销售销售开始的信息
        modelMap.addAttribute("spuSaleAttrListCheckBySku",spuSaleAttrListCheckBySku);
        // spu所拥有的sku Hash表
        modelMap.addAttribute("skuHash", itemSaleHashPackage.getSkuHash());
        // 第一行销售属性的图片
        modelMap.addAttribute("firstLineImg", itemSaleHashPackage.getFirstLineImg());
        // sku拥有几级属性
        modelMap.addAttribute("level", itemSaleHashPackage.getLevel());
        return "item";
    }
}
