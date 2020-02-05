package cn.com.gmall.controller;

import cn.com.gmall.beans.PmsBaseSaleAttr;
import cn.com.gmall.beans.PmsProductImage;
import cn.com.gmall.beans.PmsProductInfo;
import cn.com.gmall.beans.PmsProductSaleAttr;
import cn.com.gmall.constant.FastDfsConstants;
import cn.com.gmall.service.manage.SpuService;
import cn.com.gmall.util.FastDfsUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(value = "${cross-origin.address}")
public class SpuController {
    @Reference
    private SpuService spuService;

    @GetMapping("/spuList")
    public List<PmsProductInfo> getSpuList(Integer catalog3Id){
        return spuService.getSpuList(catalog3Id);
    }

    @PostMapping("/saveSpuInfo")
    public void saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);
    }

    @PostMapping("/fileUpload")
    public String fileUpload(MultipartFile file){
        return FastDfsUtil.upload(file);
    }

    @GetMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> getSpuSaleAttrList(Integer spuId){
        return spuService.getSpuSaleAttrList(spuId);
    }

    @GetMapping("/spuImageList")
    public List<PmsProductImage> getSpuImageList(Integer spuId){
        return spuService.getSpuImageList(spuId);
    }



}
