package com.chen.xiansen.r2dbc.controller;

import com.chen.xiansen.r2dbc.entity.TAuthor;
import com.chen.xiansen.r2dbc.repository.AuthorRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ConnController {

    @Resource
    AuthorRepository authorRepository;

    @GetMapping("/getConn")
    public Flux<String> getConn() {
        return authorRepository.findAllByIdIn(List.of(1L)).map(TAuthor::getName);
    }

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "hello World";
    }
}
