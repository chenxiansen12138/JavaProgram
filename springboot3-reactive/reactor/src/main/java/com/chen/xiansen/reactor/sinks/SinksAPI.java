package com.chen.xiansen.reactor.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * sinks:元素管道
 */
public class SinksAPI {
    public static void main(String[] args) throws InterruptedException {
        //unicast();
        //multicast();
        cache();
    }

    /**
     * 单播:
     * 只能有一个订阅者,当有一个以上的订阅者则会抛出异常
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
        many.asFlux().subscribe(val -> System.out.println("val = " + val));
        many.asFlux().subscribe(val -> System.out.println("val1 = " + val));
    }

    /**
     * 多播:
     * 默认情况下是从订阅的那一刻开始消费,而不是从头开始消费
     * 如果想从头开始消费,需要设置发布重放replay()
     */
    public static void multicast() throws InterruptedException {
        //Sinks.Many<Object> multi = Sinks.many().multicast().onBackpressureBuffer();//背压队列

        Sinks.Many<Object> multi = Sinks.many().replay().limit(3);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                multi.tryEmitNext("a-" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        multi.asFlux().subscribe(val -> System.out.println("val = " + val));
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            multi.asFlux().subscribe(val -> System.out.println("val1 = " + val));
        }).start();

    }

    /**
     * 缓存:
     *    如果没有调用缓存则默认缓存所有,如果调用缓存则按照调用的缓存数实行
     */
    public static void cache() throws InterruptedException {
        Flux<Integer> cache = Flux.range(1, 10).delayElements(Duration.ofSeconds(1)).cache(1);
        cache.subscribe();
        Thread.sleep(5000);
        cache.subscribe(v->{
            System.out.println("v = " + v);
        });
        Thread.sleep(10000);
    }
}
