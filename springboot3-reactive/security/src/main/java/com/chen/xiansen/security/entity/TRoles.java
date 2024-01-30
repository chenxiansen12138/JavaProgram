package com.chen.xiansen.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName t_roles
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_roles")
public class TRoles implements Serializable {

    private Long id;
    /**
    * 角色名
    */
    private String name;
    /**
    * 角色的英文名
    */

    private String value;
    /**
    * 
    */
    private Date createTime;
    /**
    * 
    */
    private Date updateTime;



}
