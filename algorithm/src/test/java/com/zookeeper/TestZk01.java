package com.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class TestZk01 {
    public static ZooKeeper zooKeeper=null;
    public static void connect(String connectionStr, int timeout) throws InterruptedException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        if (zooKeeper!=null){
            return ;
        }
        try {
            zooKeeper=new ZooKeeper(connectionStr,timeout,new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        if (zooKeeper!=null) {
            System.out.println(" 连接成功");
        }
    }
    public static void createIfNotExist(String node,String data,CreateMode createMode) throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(node, true);
        if(exists==null){
            String s = zooKeeper.create(node, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            if (s!=null){
                System.out.println(s+" :注册成功");
            }
        }
    }

    public static HostInfo getHostInfo(){
        long round = Math.round(Math.random() * 10000);
        return new HostInfo("192.168.0.1", round+"", UUID.randomUUID().toString(),"order");
    }

    public  static void register(HostInfo hostInfo) throws KeeperException, InterruptedException {
        createIfNotExist("/"+hostInfo.getServiceName(),hostInfo.getServiceName(),CreateMode.PERSISTENT);
        createIfNotExist("/"+hostInfo.getServiceName()+"/"+hostInfo.getPid(), JSON.toJSONString(hostInfo),CreateMode.EPHEMERAL);
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
        connect("192.168.6.134:2181,192.168.6.134:2182,192.168.6.134:2183", 5000);
        register(getHostInfo());
        Scanner scanner=new Scanner(System.in);
        scanner.next();
    }
}