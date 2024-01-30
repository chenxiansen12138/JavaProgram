package com.chen.xiansen.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName t_user
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("t_user")
public class TUser implements Serializable {

    /**
    * 
    */
    private Long id;
    /**
    * 用户名
    */

    private String username;
    /**
    * 密码
    */

    private String password;
    /**
    * 邮箱
    */

    private String email;
    /**
    * 电话
    */

    private String phone;
    /**
    * 创建时间
    */

    private Date createTime;
    /**
    * 更新时间
    */

    private Date updateTime;



}
