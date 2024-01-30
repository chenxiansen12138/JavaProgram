package com.chen.xiansen.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("t_perm")
public class TPerm implements Serializable {
    private Long id;
    /**
    * 权限字段
    */
    private String value;
    /**
    * 资源路径
    */
    private String uri;


    private String description;
    private Date createTime;

    private Date updateTime;

}
