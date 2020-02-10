package cn.com.gmall.manage.constens;

public class ManageUnit{
    /**
     * redis lua 脚本
     * "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end"
     */
    public static String SCRIPT = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
    /**
     * redis普通键过期时间 15分钟
     */
    public static int REDIS_KEY_EXPIRE_SECONDS = 60*15;
    /**
     * redis lock 后缀
     */
    public static final String SKU_INFO_LOCK_SUFFIX = ":lock";
    /**
     * redis lock 过期时间
     */
    public static final int LOCK_EXPIRE_TIME = 30;
    /**
     * redis lock 过期单位
     */
    public static final String LOCK_EXPIRE_UNIT = "ex";
    /**
     * redis lock 模式
     */
    public static final String LOCK_NX_XX = "nx";
    /**
     * 缓存skuInfo 前缀
     */
    public static final String SKU_INFO_PREFIX = "Sku:";
    /**
     * 缓存skuInfo 后缀
     */
    public static final String SKU_INFO_SUFFIX = ":Info";
    /**
     * 缓存ItemSaleHashPackageBySpuId前缀
     */
    public static final String ITEM_PACKAGE_PREFIX = "ItemSpu:";
    /**
     * 缓存ItemSaleHashPackageBySpuId后缀
     */
    public static final String ITEM_PACKAGE_SUFFIX = ":Hash";
    /**
     * 缓存ItemSaleHashPackageBySpuId锁
     */
    public static final String ITEM_PACKAGE_LOCK = "itemPackageLock";
}
