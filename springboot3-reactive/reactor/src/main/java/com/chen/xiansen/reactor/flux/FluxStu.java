package com.chen.xiansen.reactor.flux;

import org.reactivestreams.Subscription;
import reactor.core.publisher.*;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

/**
 * 发布者发布数据流
 * Mono: 0|1个元素的流
 * Flux: N个元素的流
 */
public class FluxStu {
    public static void main(String[] args) throws IOException, InterruptedException {
        //flux();
        //fluxBuffer();
        //limitFlux();
        //generateFlux();
        //createFlux();
        //handleFlux();
        threadFlux();
    }

    /**
     * Mono
     */
    public static void mono() {
        Mono<String> mono = Mono.just("风间由美");
        mono.subscribe(System.out::println);
    }

    /**
     * Flux
     *
     * @throws IOException
     */
    public static void flux() throws IOException, InterruptedException {
        //定义多元素的流
//        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);
//
//        //流不消费就没用; 消费:订阅
//        just.subscribe(item -> System.out.println("item -> " + item));
//        //一个数据流可以有很多消费者
//        just.subscribe(item -> System.out.println("item -> " + item));
//        System.out.println("-----------------");
//        //对于每个消费者来说流都是一样的; 广播模式
//        Flux.interval(Duration.ofSeconds(1)); //每秒产生一个从0开始的递增数字
//        just.subscribe(System.out::println);
//        //System.in.read();
//        System.out.println("-----------------");
        //空流
        // 事件感知API: 当流发生什么事的时候,触发一个回调,系统调用提前定义好的钩子函数(Hook[钩子函数]); doOnXxx;
//        Flux<Integer> flux = Flux.range(1, 7).
        Flux<Integer> flux = Flux.just(1, 2, 0, 3, 4, 5, 6, 7).map(integer -> 3 / integer).
                delayElements(Duration.ofSeconds(1))
                .doOnComplete(() -> System.out.println("流结束了"))
                .doOnCancel(() -> System.out.println("流被取消了"))
                .doOnError(throwable -> System.out.println("流出错了" + throwable))
                .doOnNext(integer -> System.out.println("doOnNext:" + integer));//有一个信号:此时代表完成信号
        flux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("元素被绑定了");
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("元素到达->" + value);
                if (value <= 4) {
                    if (value == 4) {
                        throw new RuntimeException();
                    }
                } else {
                    cancel();
                }
                request(1);
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("元素完成了");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("元素出现了异常" + throwable);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("元素被取消了");
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("全部结束了");
            }
        });
        Thread.sleep(10000);
    }

    /**
     * 请求重塑-buffer,buffer定义了每次发送的最大元素个数.
     *
     * @throws InterruptedException
     */
    public static void fluxBuffer() throws InterruptedException {
        Flux<List<Integer>> buffer = Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).buffer(3);
        buffer.subscribe(System.out::println);
        Thread.sleep(10000);
    }

    /**
     * 限流
     * 预取策略: 75%
     */
    public static void limitFlux() {
        Flux.range(1, 100).log().limitRate(30).subscribe();
    }

    /**
     * 通过编程的方式创建序列
     */
    public static void generateFlux() {
        Flux<Object> generate = Flux.generate(() -> 0, (state, sink) -> {
            if (state <= 10) {
                sink.next(state);
            } else {
                sink.complete();
            }
            return state + 1;
        });
        generate.log().subscribe();
    }

    /**
     * 多线程情况下用create创建流
     */
    public static void createFlux() {
        MyListener listener = new MyListener();
        Flux.create((sink) -> {
            for (int i = 0; i < 10; i++) {
                listener.sout("zhang-" + i);
            }
        }).subscribe();
    }

    /**
     * 自定义流的元素处理规则
     */
    public static void handleFlux() {
        Flux.range(1, 10).handle((value, sink) -> {
            sink.next("张三:" + value);
        }).log().subscribe();
    }

    /**
     * 自定义流的线程调度
     */
    public static void threadFlux() throws InterruptedException {
        //publicOn: 改变发布者所在线程池
        //subscribeOn: 改变订阅者所在线程池
//        Flux.range(1, 10).publishOn(Schedulers.single())//指定在哪一个线程池将这个流的数据和操作执行
//                .log().subscribeOn(Schedulers.single()).subscribe();
        Scheduler scheduler = Schedulers.newParallel("parallel-1", 10);
        Flux<String> fl = Flux.range(1, 50).map(integer -> integer + 1).log().publishOn(scheduler).map(integer -> "value:" + integer).log();
        new Thread(()-> fl.subscribe(System.out::println)).start();
        Thread.sleep(10000);
    }
}


class MyListener {
    FluxSink<Object> sink;

    public void MyListener(FluxSink<Object> sink) {
        this.sink = sink;
    }

    public void sout(String username) {
        System.out.println("用户登录:" + username);
    }

}
