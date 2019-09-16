package com.thread.test;

public class TestThread02 {
    private static Integer a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    shortWait(10000L);
                    synchronized (a) {
                        a += 1;
                        System.out.println("Thread1 result:" + a);
                    }
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (a) {
                        a += 1;
                        System.out.println("Thread2 result:" + a);
                    }
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if (a >= 10000) {
                System.out.println("a=" + a + ", run " + i + " times.");
                break;
            }
        }
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
