package com.chen.xiansen.reactor.context;

import reactor.core.publisher.Flux;
import reactor.util.context.Context;

public class ContextAPI {
    public static void main(String[] args) {
        Flux.just(1,2,3)
                .transformDeferredContextual((flux,context)->{
                    System.out.println("flux = " + flux);
                    System.out.println("context = " + context);
                    return flux.map(i->i+"==>"+context.get("prefix"));
                })
                //上游能拿到下游的最近一次数据
                .contextWrite(Context.of("prefix","哈哈"))
                //ThreadLocal共享了数据，上游的所有人能看到; Context由下游传播给上游
                .subscribe(v-> System.out.println("v = " + v));
    }
}
