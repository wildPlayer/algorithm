package com.algorithm.Balancing;

import com.sun.security.ntlm.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Nginx的负载均衡 - 加权轮询 (Weighted Round Robin)
 */
public class SmoothWeightedRoundRobinBalancing {
    public static List<ServerNode> serverNodeList = new ArrayList<ServerNode>();
    static{
        serverNodeList.add(new ServerNode(5, 5, "192.168.0.1"));
        serverNodeList.add(new ServerNode(1, 1, "192.168.0.2"));
        serverNodeList.add(new ServerNode(1, 1, "192.168.0.3"));
    }

    public static String getIp(){
        ServerNode serverNode = null;
        Integer total = 0;
        for(ServerNode serverNode1 : serverNodeList){
            if(serverNode == null || serverNode.getValidWeighted() < serverNode1.getValidWeighted()){
                serverNode = serverNode1;
            }
            total += serverNode1.getDefaultWeighted();
        }
//        System.out.println("largest node:" + serverNode.toString());
        for(ServerNode serverNode1 : serverNodeList){
            if(serverNode1 == serverNode){
                serverNode1.setValidWeighted(serverNode1.getValidWeighted() - total
                        + serverNode1.getDefaultWeighted());
            }else{
                serverNode1.setValidWeighted(serverNode1.getValidWeighted() + serverNode1.getDefaultWeighted());
            }
        }
//        System.out.println("total:" + total);
//        System.out.println("serverNode:");
//        for(ServerNode sn : serverNodeList){
//            System.out.println(sn.toString());
//        }
        serverNode = null;
        for(ServerNode serverNode1 : serverNodeList){
            if(serverNode == null || serverNode.getValidWeighted() < serverNode1.getValidWeighted()){
                serverNode = serverNode1;
            }
        }
        return serverNode.getIp();

    }

    public static void main(String[] args){
        for(int i=0;i<100;i++){
            System.out.println(SmoothWeightedRoundRobinBalancing.getIp());
        }
    }
}
