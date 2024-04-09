package com.chen.xiansen.service;

import com.chen.xiansen.entities.Pay;

import java.util.List;

public interface PaymentService {
    public int add(Pay pay);

    public int delete(Integer id);

    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();

}
