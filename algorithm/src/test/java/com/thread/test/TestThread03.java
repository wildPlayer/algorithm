package com.thread.test;

import org.junit.Test;

public class TestThread03 {
    volatile boolean initFlag = false;
    public void save(){
        this.initFlag = true;
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + ", update initFlag");
    }
    public void load(){
        String threadName = Thread.currentThread().getName();
        while(!initFlag){

        }
        System.out.println("initFlag has been changed");
    }
    public static void main(String[] args){
        TestThread03 testThread03 = new TestThread03();
        Thread threadA = new Thread(()->{testThread03.save();}, "ThreadA");
        Thread threadB = new Thread(()->{testThread03.load();}, "ThreadB");
        threadB.start();
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }threadA.start();
    }

    @Test
    public void tt(){
        System.out.println(System.currentTimeMillis());
    }
}
