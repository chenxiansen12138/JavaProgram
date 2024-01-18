package com.chen.xiansen.reactor.sinks;

import reactor.core.publisher.Sinks;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class SinksAPI {
    public static void main(String[] args) {
        unicast();
    }

    /**
     * 单播:
     *      只能有一个订阅者,当有一个以上的订阅者则会抛出异常
     */
    public static void unicast() {
        Sinks.Many<Object> many = Sinks.many().unicast().onBackpressureBuffer(new LinkedBlockingDeque<>(5));
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                many.tryEmitNext("a-" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        many.asFlux().subscribe(val-> System.out.println("val = " + val));
        many.asFlux().subscribe(val-> System.out.println("val1 = " + val));
    }
}
