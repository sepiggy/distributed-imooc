package com.imooc.distributedlimiter.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.distributedlimiter.service.LimiterService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;

@RestController
@Slf4j
public class LimiterController {

    @Autowired
    private LimiterService limiterService;

    private RateLimiter rateLimiter = RateLimiter.create(10);

    private LongAdder longAdder = new LongAdder();

    /**
     * 使用 RateLimiter 作限流
     */
    @GetMapping("/guava3")
    public void guava3() {
        if (rateLimiter.tryAcquire()) {
            // 模拟扣库存，下单...
            log.info("恭喜你，抢到了...");
        } else {
            log.info("不好意思，手慢了，没抢到...");
        }
    }

    @GetMapping("/guava2")
    public void guava2() {
        longAdder.increment();
        val index = longAdder.sum();
        log.info("{} guava...", index);
    }

    @GetMapping("/guava1")
    public void guava1() {
        val rl = RateLimiter.create(2);

        log.info("{}", rl.acquire(3));
        log.info("{}", rl.acquire());
        log.info("{}", rl.acquire());
        log.info("{}", rl.acquire());
    }

    @GetMapping("/guava")
    public void guava() {
        // 创建可放2个令牌的桶且每秒放2个令牌进入桶内(也就是0.5秒放1个令牌)
//        RateLimiter rateLimiter = RateLimiter.create(2);
//
//        //平滑输出，允许突发流量
//        log.info("{}", rateLimiter.acquire(3));
//        log.info("{}", rateLimiter.acquire());
//        log.info("{}", rateLimiter.acquire());
//        log.info("{}", rateLimiter.acquire());

        boolean tryAcquire = rateLimiter.tryAcquire();
        if (tryAcquire) {
            //扣库存，下单....
            log.info("恭喜你，抢到了！");
        } else {
            log.info("不好意思，你手慢了，没抢到");
        }
    }

    @GetMapping("/sentinel")
    public String sentinel() {
        limiterService.process();
        return "sentinel";
    }
}
