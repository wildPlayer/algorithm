package com.algorithm.Balancing;

public class ServerNode {
    private Integer defaultWeighted;
    private Integer validWeighted;
    private String ip;

    public ServerNode(Integer defaultWeighted, Integer validWeighted, String ip) {
        this.defaultWeighted = defaultWeighted;
        this.validWeighted = validWeighted;
        this.ip = ip;
    }

    public Integer getDefaultWeighted() {
        return defaultWeighted;
    }

    public void setDefaultWeighted(Integer defaultWeighted) {
        this.defaultWeighted = defaultWeighted;
    }

    public Integer getValidWeighted() {
        return validWeighted;
    }

    public void setValidWeighted(Integer validWeighted) {
        this.validWeighted = validWeighted;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "defaultWeighted=" + defaultWeighted +
                ", validWeighted=" + validWeighted +
                ", ip='" + ip + '\'' +
                '}';
    }
}
