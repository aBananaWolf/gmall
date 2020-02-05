package cn.com.gmall.controller;

import cn.com.gmall.beans.PmsProductSaleAttr;
import cn.com.gmall.beans.PmsSkuInfo;
import cn.com.gmall.beans.PmsSkuSaleAttrValue;
import cn.com.gmall.service.manage.SkuService;
import cn.com.gmall.service.manage.SpuService;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.util.AuthResources_it;

import java.util.*;

@Controller
public class ItemController {
    @Reference
    private SkuService skuService;
    @Reference
    private SpuService spuService;

    @RequestMapping("/{skuId}.html")
    public String toItemPage(@PathVariable Integer skuId,ModelMap modelMap){
        PmsSkuInfo skuInfo = skuService.getSkuInfo(skuId);
        modelMap.addAttribute("skuInfo",skuInfo);
        List<PmsProductSaleAttr> spuSaleAttrListCheckBySku = spuService.getSpuSaleAttrListCheckBySku(skuInfo.getId(), skuInfo.getSpuId());
        List<PmsSkuInfo> saleHash = skuService.getSaleAttrValueByHash(skuInfo.getSpuId());
        HashMap<String, String> map = new HashMap<>();
        LinkedHashSet<String> img = new LinkedHashSet<>();
        for (PmsSkuInfo hash : saleHash) {
            StringBuilder stringBuilder = new StringBuilder();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : hash.getSkuSaleAttrValueList()) {
                stringBuilder.append(pmsSkuSaleAttrValue.getSaleAttrValueId());
                stringBuilder.append("|");
            }
            map.put(stringBuilder.toString(),String.valueOf(hash.getId()));
            img.add(hash.getSkuDefaultImg());
        }
        PmsProductSaleAttr pmsProductSaleAttr = spuSaleAttrListCheckBySku.get(0);
        modelMap.addAttribute("spuSaleAttrListCheckBySku2",pmsProductSaleAttr);
        spuSaleAttrListCheckBySku.remove(0);
        modelMap.addAttribute("spuSaleAttrListCheckBySku",spuSaleAttrListCheckBySku);
        modelMap.addAttribute("saleHash", JSON.toJSON(map));
        modelMap.addAttribute("allSkuDefaultImg", JSON.toJSON(img));
        modelMap.addAttribute("checked", saleHash.get(0).getSkuSaleAttrValueList().size());
        return "item";
    }

   /* @RequestMapping("/test")
    public String test(ModelMap modelMap){
        modelMap.addAttribute("index","hello!thymeleaf!!");
        modelMap.addAttribute("check",1);
        modelMap.addAttribute("script","hello!JavaScript!!!");
        ArrayList<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        modelMap.addAttribute("list",list);
        return "index";
    }
*/
}
