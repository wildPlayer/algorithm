package com.algorithm.test;

import org.junit.Test;

import java.lang.reflect.Field;

public class Test02 {
    public static void main(String[] args) {
        String[] str = {"a", "b"};
        TT(str);
        System.out.println(str[0]);
    }

    public static void TT(String[] args){
        int a = 1;
        int b = a;
        args[0] = "tt";
    }

    public  void ts(){
        int s = 2;
        System.out.println(s + 3);
    }

    @Test
    public void testDemoA(){
        Field[] fields = DemoA.class.getDeclaredFields();
        for(Field f : fields){
            System.out.println(f.getName());
        }
        try {
            Field field = DemoA.class.getDeclaredField("strx");
            System.out.println(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(DemoA.class.getName());
        System.out.println(DemoA.class.getSimpleName());
    }

    @Test
    public void testDemoB(){
        try {
            Class<?> demoA = Class.forName("DemoA");
            System.out.println(demoA);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class DemoA{
    private String str1;
    private String str2;

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }
}