package com.algorithm.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  LRU(Least recently used,最近最少使用)算法
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;

    public LRUCache(int cache_size, boolean accessOrder){
        super((int)Math.ceil(cache_size/0.75) + 1, 0.75f, accessOrder);
        this.MAX_CACHE_SIZE = cache_size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest){
        System.out.println("remove entry: " + eldest.getKey() + "-" + eldest.getValue());
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s | ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }

}
