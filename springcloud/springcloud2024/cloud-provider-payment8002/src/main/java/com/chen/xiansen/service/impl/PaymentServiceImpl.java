package com.chen.xiansen.service.impl;

import com.chen.xiansen.entities.Pay;
import com.chen.xiansen.mapper.PayMapper;
import com.chen.xiansen.service.PaymentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PayMapper mapper;

    @Override
    public int add(Pay pay) {
        return mapper.insert(pay);
    }

    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);

    }

    @Override
    public int update(Pay pay) {
        return mapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return mapper.selectAll();
    }
}
