package com.chen.xiansen.springsecurity6.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.xiansen.springsecurity6.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
