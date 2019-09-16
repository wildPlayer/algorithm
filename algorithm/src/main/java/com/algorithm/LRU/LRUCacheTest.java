package com.algorithm.LRU;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<String, String>(10, true);
        for(int i=0;i<20;i++){
            lruCache.put("lru" + i%10, "test" + i%10);
            System.out.println(lruCache.toString());
        }
        System.out.println();
        lruCache.put("lru" + 4, "test" + 4);
        System.out.println(lruCache.toString());
        lruCache.put("lru" + 7, "test" + 7);
        System.out.println(lruCache.toString());
        lruCache.put("lru" + 2, "test" + 2);
        System.out.println(lruCache.toString());
    }
}
