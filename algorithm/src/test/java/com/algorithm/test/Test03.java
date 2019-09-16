package com.algorithm.test;

import com.alipay.api.internal.util.StringUtils;
import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test03 {

    @Test
    public void testHashMap(){
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("n1", "v1");
        map.put("n2", "v2");
        map.put("n3", "v3");
        map.put("n4", "v4");
    }

    @Test
    public void testHash(){
        Nd[] oldTab = new Nd[4];
        Map<String, Nd> map = new HashMap<String, Nd>();
        Nd n1 = new Nd("n1", "n1v", "n2");
        Nd n2 = new Nd("n2", "n2v", "n3");
        Nd n3 = new Nd("n3", "n3v", "");
        Nd n4 = new Nd("n4", "n4v", "n5");
        Nd n5 = new Nd("n5", "n5v", "");
        Nd n6 = new Nd("n6", "n6v", "");
        map.put("n1", n1);
        map.put("n2", n2);
        map.put("n3", n3);
        map.put("n4", n4);
        map.put("n5", n5);
        map.put("n6", n6);
        oldTab[0] = n1;
        oldTab[1] = n4;
        oldTab[2] = n6;

        Nd[] newTab = new Nd[8];
        Integer oldCap = 4;
        Integer newCap = 8;
        System.out.println("oldTab:");
        for(int i=0;i<oldCap;i++){
            if(oldTab[i] != null){
                System.out.println(oldTab[i].toString());
            }
        }

        for (int j = 0; j < oldTab.length; ++j) {
            Nd e;
            //如果发现当前有Bucket
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                //如果这里没有碰撞
                if (StringUtils.isEmpty(e.getNext()))
                    //重新计算Hash，分配位置
                    newTab[e.hashCode() & (newCap - 1)] = e;
                    //这个见下面的新特性介绍，如果是树，就填入树
                else { // preserve order
                    Nd loHead = null, loTail = null;
                    Nd hiHead = null, hiTail = null;
                    Nd next;
                    do {
                        next = map.get(e.getNext());
                        if ((e.hashCode() & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.setNext(e.getKey());
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.setNext(e.getKey());
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.setNext(null);
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.setNext(null);
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
        System.out.println("newTab:");
        for(int i=0;i<newCap;i++){
            if(newTab[i] != null){
                System.out.println(newTab[i].toString());
            }
        }
    }
}

class Nd{
    private String key;
    private String value;
    private String next;

    public Nd(String key, String value, String next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Nd{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}