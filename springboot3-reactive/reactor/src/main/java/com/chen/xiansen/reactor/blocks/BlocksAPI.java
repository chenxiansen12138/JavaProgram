package com.chen.xiansen.reactor.blocks;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * 阻塞式API
 */
public class BlocksAPI {
    public static void main(String[] args) {
        //all();
        paralleFlux();
    }

    /**
     * block获取所有元素
     */
    public static void all() {
        List<Integer> block = Flux.range(1, 10).collectList().block();
        assert block != null;
        block.forEach(System.out::println);
    }

    /**
     * 并发运行
     */
    public static void paralleFlux() {
        Flux.range(1,1000)
                .buffer(100)
                .parallel(10)
                .runOn(Schedulers.newParallel("yyy"))
                .log()
                .subscribe();
    }
}
