package cn.com.gmall.manage.controller;

import cn.com.gmall.beans.PmsBaseAttrInfo;
import cn.com.gmall.beans.PmsBaseAttrValue;
import cn.com.gmall.service.manage.BaseAttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(value = "${cn.com.address}")
public class BaseAttrController {
    @Reference
    private BaseAttrService baseAttrService;

    @GetMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(Integer catalog3Id){
        return baseAttrService.attrInfoList(catalog3Id);
    }
    @RequestMapping("/saveAttrInfo")
    public void saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        baseAttrService.saveOrUpdateAttrInfo(pmsBaseAttrInfo);
    }
    @PostMapping("/getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(Integer attrId){
        return baseAttrService.getAttrValueList(attrId);
    }
}
