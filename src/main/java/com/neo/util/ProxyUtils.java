package com.neo.util;

import com.alibaba.fastjson.JSONObject;
import com.neo.util.proxy.ProxyItem;
import com.neo.util.proxy.ProxyList;
import okhttp3.OkHttpClient;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author moxianbin
 * @date 2019/3/7.
 */
public class ProxyUtils {

    private final static org.slf4j.Logger log = LoggerFactory.getLogger(ProxyUtils.class);

    private static OkHttpClient httpClient;

    private static String getProxyListUrl = "http://120.77.202.72:8899/api/v1/proxies?https=true";

    public static ProxyList getProxyList(){
        String proxyListStr = HttpClient.get(getProxyListUrl);
        return JSONObject.parseObject(proxyListStr, ProxyList.class);
    }

    public static ProxyItem getRandomProxyItem(List<ProxyItem> proxies){
        if (Objects.isNull(proxies) || proxies.size() == 0) {
            return null;
        }

        Random random = new Random();
        return proxies.get(random.nextInt(proxies.size()));
    }

    public static ProxyItem getRandomProxyItem(){
        ProxyList proxyList = getProxyList();
        List<ProxyItem> proxies = proxyList.getProxies();
        if (Objects.isNull(proxies) || proxies.size() == 0) {
            return null;
        }

        Random random = new Random();
        return proxies.get(random.nextInt(proxies.size()));
    }

}
