package com.okhttp.demo.utils;

public interface Constants {

    String quickAi = "b23095a9beeb1fad29a78940e8ea5767";
    String MEIZU_QUICK_APP_DOMAIN_NAME = "miniapp-api.meizu.com";

    String PARA_SERVICES = "services";
    String PARA_TIMESTAMP = "timestamp";
    String PARA_SIGN = "sign";

    String CACHE_GAME_RECENT = "cache_game_recent";

    /**
     * 请求接口基础参数定义
     */
    String PARA_PRODUCT_TYPE = "productType";
    String PARA_DEVICE_ID = "deviceId";//imei
    String PARA_SN = "sn";
    String PARA_VERSION_CODE = "versionCode";
    String PARA_PACKAGE_NAME = "packageName";
    String PARA_PACKAGE_NAMES = "packageNames";
    String PARA_LIMIT = "limit";
    String PARA_START = "start";
    String PARA_LEN = "len";
    String PARA_SOURCE_HOST = "sourceHost";//宿主应用包名
    String PARA_PLATEFORM_VERSION = "platformVersion";
    String PARA_NETWORK_STATUS = "networkStatus";
    String PARA_FLYME_VERSION = "flymeVersion";
    String PARA_ANDROID_VERSION = "androidVersion";
    String PARA_TYPE = "type";
    String PARA_SDKVERSION = "sdkVersion";//code
    String PARA_BRAND = "brand";
    String PARA_GAME_PN = "gamePn";


    /**
     * 请求接口其它参数定义
     */
    String PARA_OTHER_SOURCE = "source";
    String PARA_OTHER_RECOMMANDTYPE = "recommendType";
    String PARA_KEY_WORD = "keyWord";
    String PARA_APPID = "appId";
    String PARA_TABID = "tabId";
    String PARA_BLOCKID = "blockId";
    String PARA_REGIONID = "regionId";
    String PARA_ISMORE = "isMore";
    String PARA_SHOW_USERINFO = "isShowUserInfo";

    /**
     * 卡片列表保存字段名
     */
    String LANCH_GAME_ACTION = ".action.LAUNCH_GAME";
    String QUICK_APP_LAUNCH_ACTION = "org.sodajs.action.action.LAUNCH_APP";
    String QUICK_GAME_RPK_PKG_NAME = "com.meizu.quick.game";

    String EXTRA_URL = "EXTRA_URL";
    String EXTRA_APP = "EXTRA_APP";
    String EXTRA_PATH = "EXTRA_PATH";
    String EXTRA_VERSION = "EXTRA_VERSION";
    String EXTRA_LAUNCH_ENTRY = "EXTRA_LAUNCH_ENTRY";


    interface CALENDAR {
        String PKG_NAME = "com.android.calendar";
    }

    interface CardCacheHandle {
        String USER_CACHE_CARD_SHOW_MAX = "user_cache_card_show_max";
        String HOME_CACHE_KEY = "home_cache_key";
        String HOME_CACHE_UPDATE_TIME = "home_cache_update_time";
        long HOME_CACHE_EXPIRED_TIME = 4*3600*1000;//磁盘缓存过期时间
    }

    /**
     * 引擎支持的游戏前缀
     */
    String GAME_URL_BASE = "uphap://upgame/";

    /**
     * 记录用户使用时间的sp文件的key
     */
    String SP_TOTAL_USE_TIME = "total_use_time_";
}