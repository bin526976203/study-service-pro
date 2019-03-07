package com.neo.util.proxy;

/**
 * @author moxianbin
 * @date 2019/3/7.
 */
public class ProxyItem {
    private int id;
    private String ip;
    private int port;
    private boolean is_valid;
    private int latency;
    private boolean is_https;
    /**
     * 是否匿名
     */
    private boolean is_anonymous;
    private double stability;

    public ProxyItem(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean is_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public boolean is_https() {
        return is_https;
    }

    public void setIs_https(boolean is_https) {
        this.is_https = is_https;
    }

    public boolean is_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public double getStability() {
        return stability;
    }

    public void setStability(double stability) {
        this.stability = stability;
    }
}
