package cn.com.gmall.manage.service.impl;

import cn.com.gmall.beans.*;
import cn.com.gmall.manage.constens.ManageUnit;
import cn.com.gmall.manage.mapper.*;
import cn.com.gmall.manage.service.manage.SkuService;
import cn.com.gmall.util.JedisUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.dubbo.config.annotation.Service;
import org.apache.http.HttpRequest;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private SpuSaleAttrListCheckBySkuMapper saleAttrMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private RedissonClient redissonClient;


    @Override
    @Transactional
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
    public PmsSkuInfo getSkuInfo(Long skuId, boolean skuImg, boolean getSpuSaleAttrListCheckBySku) {
        PmsSkuInfo skuInfoFromDB = null;
        Jedis jedis = jedisUtil.getJedis();
        try {
            String key = ManageUnit.SKU_INFO_PREFIX+skuId+ManageUnit.SKU_INFO_SUFFIX;
            String skuInfoFromDBStr = jedis.get(key);
            if(skuInfoFromDBStr!=null){
//                System.out.println("==命中缓存=="+Thread.currentThread().getId());
                skuInfoFromDB = JSON.parseObject(skuInfoFromDBStr,PmsSkuInfo.class);
            }else{
                String lockKey = ManageUnit.SKU_INFO_PREFIX+skuId+ManageUnit.SKU_INFO_LOCK_SUFFIX;
                String lockValue = UUID.randomUUID().toString();
                // 分布式锁
                String lock = jedis.set(lockKey, lockValue, ManageUnit.LOCK_NX_XX, ManageUnit.LOCK_EXPIRE_UNIT, ManageUnit.LOCK_EXPIRE_TIME);
                if("OK".equals(lock)){
//                    System.out.println("==没有命中缓存，获得锁=="+Thread.currentThread().getId());
                    skuInfoFromDB = this.getSkuInfoFromDB(skuId, skuImg, getSpuSaleAttrListCheckBySku);
                    if(skuInfoFromDB==null){
                        // 缓存空串，避免缓存穿透
                        jedis.setex(key,180,"");
                        skuInfoFromDB=new PmsSkuInfo();
                    }else{
//                        System.out.println("==缓存成功=="+Thread.currentThread().getId());
                        jedis.setex(key,ManageUnit.REDIS_KEY_EXPIRE_SECONDS,JSON.toJSONString(skuInfoFromDB));
                    }
                    jedis.eval(ManageUnit.SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(lockValue));
                }else{
//                    System.out.println("==没有命中缓存，没有获得锁，自旋=="+Thread.currentThread().getId());
                    try {
                    }finally {
//                        jedis.close();
//                        jedis=null;
                        try {
                            Random random = new Random();
                            Thread.sleep(random.nextInt(2888));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return this.getSkuInfo(skuId, skuImg, getSpuSaleAttrListCheckBySku);
                }
            }
        } finally {
//            if(jedis!=null){
                jedis.close();
//            }
        }
//        System.out.println("==返回结果=="+Thread.currentThread().getId());
        return skuInfoFromDB;
    }
    public PmsSkuInfo getSkuInfoFromDB(Long skuId,boolean skuImg,boolean getSpuSaleAttrListCheckBySku) {
        Example example = new Example(PmsSkuInfo.class);
        example.createCriteria().andEqualTo("id",skuId);
        PmsSkuInfo skuInfo = infoMapper.selectOneByExample(example);
        if(skuImg){
            Example imgExample = new Example(PmsSkuImage.class);
            imgExample.createCriteria().andEqualTo("skuId",skuId);
            List<PmsSkuImage> pmsSkuImages = imageMapper.selectByExample(imgExample);
            skuInfo.setSkuImageList(pmsSkuImages);
        }
        if(getSpuSaleAttrListCheckBySku){
            skuInfo.setSpuSaleAttrListCheckBySku(this.getSpuSaleAttrListCheckBySku(skuInfo.getId(),skuInfo.getSpuId()));
        }
        return skuInfo;
    }
    /**
     * 查询出销售属性列表并默认选中
     * @param skuId
     * @param spuId
     * @return
     */
    public List<PmsProductSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        return saleAttrMapper.selectSpuSaleAttrListCheckBySku(skuId, spuId);
    }


    @Override
    public ItemSaleHashPackage getItemSaleHashPackageBySpuId(Long spuId) {
        ItemSaleHashPackage itemSaleHashPackage = null;
        Jedis jedis = jedisUtil.getJedis();
        try {
            String s = jedis.get(ManageUnit.ITEM_PACKAGE_PREFIX + spuId + ManageUnit.ITEM_PACKAGE_SUFFIX);
            if(s != null){
                itemSaleHashPackage = JSON.parseObject(s,ItemSaleHashPackage.class);
            }else{//ManageUnit.ITEM_PACKAGE_LOCK
                RLock lock = redissonClient.getLock(ManageUnit.ITEM_PACKAGE_LOCK);
                lock.lock(3, TimeUnit.SECONDS);
                try {
                    itemSaleHashPackage = this.getSaleAttrValueByHashFromDB(spuId);
                    if(itemSaleHashPackage == null){
                        jedis.set(ManageUnit.ITEM_PACKAGE_PREFIX + spuId + ManageUnit.ITEM_PACKAGE_SUFFIX,"");
                    }else{
                        jedis.setex(ManageUnit.ITEM_PACKAGE_PREFIX + spuId + ManageUnit.ITEM_PACKAGE_SUFFIX,ManageUnit.LOCK_EXPIRE_TIME,JSON.toJSONString(itemSaleHashPackage));
                    }
                } finally {
                    lock.unlock();
                }
            }
        } finally {
            jedis.close();
        }
        return itemSaleHashPackage;
    }

    @Override
    public List<PmsSkuInfo> getAllSkuInfo() {
        List<PmsSkuInfo> pmsSkuInfos = infoMapper.selectAll();
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            Example example = new Example(PmsSkuSaleAttrValue.class);
            example.createCriteria().andEqualTo("skuId",pmsSkuInfo.getId());
            pmsSkuInfo.setSkuAttrValueList(attrValueMapper.selectByExample(example));
        }
        return pmsSkuInfos;
    }


    public ItemSaleHashPackage getSaleAttrValueByHashFromDB(Long spuId) {
        List<PmsSkuInfo> saleHash = infoMapper.selectSaleAttrValueByHash(spuId);
        ItemSaleHashPackage itemSaleHashPackage = this.generateSkuHashAndImg(saleHash);
        return itemSaleHashPackage;
    }
    // HashMap<String, String> skuHashMap, LinkedHashSet<String> firstLineImg
    public ItemSaleHashPackage generateSkuHashAndImg(List<PmsSkuInfo> saleHash){
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
        ItemSaleHashPackage itemSaleHashPackage = new ItemSaleHashPackage();
        itemSaleHashPackage.setSkuHash(JSON.toJSON(map).toString());
        itemSaleHashPackage.setFirstLineImg(img);
        itemSaleHashPackage.setLevel(saleHash.get(0).getSkuSaleAttrValueList().size());
        return itemSaleHashPackage;
    }


}
