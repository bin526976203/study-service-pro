package com.neo.util.proxy;

import java.util.List;

/**
 * @author moxianbin
 * @date 2019/3/7.
 */
public class ProxyList {

    private List<ProxyItem> proxies;

    private int count;

    private int per_page;

    private int page;

    private int total_page;

    public ProxyList(){}

    public List<ProxyItem> getProxies() {
        return proxies;
    }

    public void setProxies(List<ProxyItem> proxies) {
        this.proxies = proxies;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }
}
