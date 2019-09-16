package com.zookeeper;

public class HostInfo {
    private String hostIp;
    private String port;
    private String pid;
    private String serviceName;


    public HostInfo(String hostIp, String port, String pid, String serviceName) {
        this.hostIp = hostIp;
        this.port = port;
        this.pid = pid;
        this.serviceName = serviceName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
