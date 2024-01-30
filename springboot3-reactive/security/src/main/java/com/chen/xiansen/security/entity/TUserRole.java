package com.chen.xiansen.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName t_user_role
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("t_user_role")
public class TUserRole implements Serializable {

    private Long id;

    private Long userId;

    private Long roleId;

    private Date createTime;
    /**
    * 
    */
    private Date updateTime;


}
