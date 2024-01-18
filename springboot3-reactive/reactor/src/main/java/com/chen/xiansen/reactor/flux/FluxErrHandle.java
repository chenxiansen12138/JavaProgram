package com.chen.xiansen.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxErrHandle {
    public static void main(String[] args) {
        //onErrorReturn();
        //onErrorResume();
        //onErrorMap();
        //doOnError();
        onErrorContinue();
    }

    /**
     * onErrorReturn:
     * 1.处理异常,消费者感知不到异常
     * 2.返回一个默认值,也可以传一个异常的类作为参数
     * 3.流正常完成
     */
    public static void onErrorReturn() {
        Flux.just(1, 2, 0, 4).map(v -> 100 / v + "").onErrorReturn(NullPointerException.class, "出错了").subscribe(System.out::println, err -> System.out.println(err.getMessage()), () -> System.out.println("流结束了"));
    }

    /**
     * onErrorResume:
     * 1.处理异常,消费者感知不到异常
     * 2.调用一个方法
     * 3.流正常完成
     */
    public static void onErrorResume() {
        Flux.just(1, 2, 0, 4).map(v -> 100 / v + "").onErrorResume(err -> Mono.just("aaa")).subscribe(System.out::println, err -> System.out.println(err.getMessage()), () -> System.out.println("流结束了"));
    }

    /**
     * onErrorMap
     * 1.处理异常,消费者能感知到异常
     * 2.调用一个方法,将原来的异常转为一个新(自定义)的异常
     * 3.流异常完成
     */
    public static void onErrorMap() {
        Flux.just(1, 2, 0, 4).map(v -> 100 / v + "").onErrorMap(err -> new BusinessException("GG")).subscribe(System.out::println, err -> System.out.println(err.getMessage()), () -> System.out.println("流结束了"));
    }

    /**
     * doOnError:
     * 1.异常被捕获、做自己的事情
     * 2.不影响异常继续顺着流水线传播
     * 3.不处理异常，只在异常发生的时候做一件事，消费者有感知
     */
    public static void doOnError() {
        Flux.just(1, 2, 0, 4).map(v -> 100 / v + "").doOnError(err -> System.out.println("err = " + err)).subscribe(System.out::println, err -> System.out.println(err.getMessage()), () -> System.out.println("流结束了"));
    }

    /**
     * onErrorContinue:
     * 忽略当前异常，仅通知记录，继续推进
     */
    public static void onErrorContinue() {
        Flux.just(1, 2, 0, 4).map(v -> 100 / v + "").onErrorContinue((err, val) -> {
            System.out.println("err:" + err);
            System.out.println("val:" + val);
        }).subscribe(System.out::println, err ->
                        System.out.println(err.getMessage()),
                () -> System.out.println("流结束了"));
    }

    /**
     * onErrorComplete:
     *   流在遇到错误的时候会直接结束,消费者不会感知到异常
     * onErrorStop:
     *   流错误时停止,即使是正常的消费者也会停止
     */
    public static void onErrorComplete() {
        Flux.just(1, 2, 0, 4).map(v -> 100 / v + "").
                onErrorComplete().subscribe(System.out::println, err ->
                        System.out.println(err.getMessage()),
                () -> System.out.println("流结束了"));
    }
}

class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
