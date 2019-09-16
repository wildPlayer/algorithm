package com.algorithm.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Test01 {

    @Test
    public void testDouble(){
        Double a = 0.3d;
        Double b = 0.1d;
        System.out.println("a - b:" + (a - b));

        Double d = 0.3d;
        BigDecimal bd = new BigDecimal(d);
        System.out.println("bd   :" + bd);
    }

    @Test
    public void testHashMap(){
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("wait to run");
                Thread.sleep(1000L);
                for(int i=0;i<10;i++){
                    map.put("First" + i, "one" + i);
                    System.out.println(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i=0;i<10;i++){
                map.put("Second" + i, "two" + i);
                System.out.println(map);
            }
        });
        try{
            thread1.start();
            thread2.start();
            System.out.println(map);
            System.out.println(map.toString());
        } catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }

}
