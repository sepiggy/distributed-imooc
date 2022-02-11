package com.imooc.distributedjob.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJob {

    @Autowired
    private Process process;

    //initialDelay 延迟启动
    //fixedDelay 固定延迟，时间间隔是前次任务的结束到下次任务的开始
    //fixedRate 时间间隔是前次任务和下次任务的开始
//    @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    //crontab  https://cron.qqe2.com/
//    @Scheduled(cron = "0,5 * * * * ?")
//    public void process() throws InterruptedException {
//        log.info("process....start");
////        Thread.sleep(2000);
////        log.info("process....stop");
//    }

    @Scheduled(fixedRate = 3000)
    public void process1() throws InterruptedException {
        log.info("process1...start");
        process.doProcess1();
        log.info("process1...stop");
    }

//    @Scheduled(fixedRate = 3000)
//    public void process2() throws InterruptedException {
//        log.info("process2...start");
//        Thread.sleep(5000);
//        log.info("process2...stop");
//    }

}
