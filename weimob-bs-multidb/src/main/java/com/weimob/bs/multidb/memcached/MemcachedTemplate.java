package com.weimob.bs.multidb.memcached;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.util.concurrent.ExecutionException;

/**
 * Created by Adam on 2016/2/17.
 */
public class MemcachedTemplate {

    private MemcachedClient memcachedClient;

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public MemcachedTemplate() {

    }

    public void set(String key, int exp, String o) throws ExecutionException, InterruptedException {
        OperationFuture future = this.memcachedClient.set(key, exp, o);
        future.get();
    }

    public String get(String key) {
        Object result = this.memcachedClient.get(key);
        if (result != null) {
            return result.toString();
        } else {
            return "";
        }
    }

    public void delete(String key) throws ExecutionException, InterruptedException {
        OperationFuture future = this.memcachedClient.delete(key);
        future.get();
    }
}
