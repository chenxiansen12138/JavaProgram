package com.chen.xiansen.component;

import com.chen.xiansen.feign.GatewayFeignApi;
import com.chen.xiansen.response.ResultData;
import com.chen.xiansen.response.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class GatewayFailFallback implements GatewayFeignApi {
    @Override
    public ResultData getById(String id) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "出现问题跑到这里了");
    }

    @Override
    public ResultData<String> getGatewayInfo() {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "出现问题跑到这里了");
    }
}
