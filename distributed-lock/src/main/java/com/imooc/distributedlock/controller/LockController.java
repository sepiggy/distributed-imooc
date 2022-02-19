package com.imooc.distributedlock.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lock2")
    public void lock2() {
        val lock = redissonClient.getLock("lock");
        try {
            val b = lock.tryLock();
            if (b) { // 拿到锁
                log.info("开始下单...");
                log.info("模拟业务逻辑...");
                Thread.sleep(9000);
                lock.unlock();
                log.info("解锁了...");
            } else {
                log.info("很遗憾...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @GetMapping("/lock")
    public void lock() {
        val lock = redissonClient.getLock("lock");
        try {
            lock.lock();
            Thread.sleep(3000); // 模拟业务逻辑
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("解锁了...");
        }
    }
}
