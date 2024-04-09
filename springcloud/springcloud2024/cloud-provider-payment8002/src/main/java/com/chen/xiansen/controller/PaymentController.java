package com.chen.xiansen.controller;

import com.alibaba.fastjson2.JSON;
import com.chen.xiansen.entities.Pay;
import com.chen.xiansen.model.PaymentDTO;
import com.chen.xiansen.response.ResultData;
import com.chen.xiansen.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Tag(name = "支付微服务模块")
public class PaymentController {
    @Resource
    private PaymentService service;

    @PostMapping("/add")

    public ResultData<String> add(@RequestBody Pay pay) {
        int add = service.add(pay);
        return ResultData.success("插入成功!插入" + add + "条");
    }

    @DeleteMapping("/delete/{id}")
    public ResultData<String> delete(@PathVariable("id") String id) {
        int delete = service.delete(Integer.parseInt(id));
        return ResultData.success("删除成功!删除" + delete + "条");
    }

    @PutMapping("/update")
    public ResultData<String> update(@RequestBody PaymentDTO paymentDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(paymentDTO, pay);
        int update = service.update(pay);
        return ResultData.success("修改成功!修改" + update + "条");
    }

    @GetMapping("/get")
    public ResultData<String> get() {
        return ResultData.success(JSON.toJSONString(service.getAll()));
    }

    @GetMapping("/get/{id}")
    public ResultData<String> getOne(@PathVariable("id") String id) {
        return ResultData.success(JSON.toJSONString(service.getById(Integer.parseInt(id))));
    }

    @GetMapping("/loadbalance")
    public String loadbalance() {
        log.info(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        if (1 == 1) {
            try {
                TimeUnit.SECONDS.sleep(5);
                throw new RuntimeException("查看日志和重试是否生效");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                log.info(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            }
        }
        return "负载均衡:本回访问了8002端口!!!";
    }
}
