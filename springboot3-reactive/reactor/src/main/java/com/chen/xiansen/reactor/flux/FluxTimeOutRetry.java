package com.chen.xiansen.reactor.flux;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 超时与重试
 */
public class FluxTimeOutRetry {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 3).delayElements(Duration.ofSeconds(3))
                .log()
                .timeout(Duration.ofSeconds(2))
                .retry(3).log().subscribe();
        TimeUnit.SECONDS.sleep(50);
    }
}
