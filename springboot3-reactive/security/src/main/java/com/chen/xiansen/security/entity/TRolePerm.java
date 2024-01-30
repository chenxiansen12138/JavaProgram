package com.chen.xiansen.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_role_perm")
public class TRolePerm implements Serializable {

    private Long id;
    private Long roleId;

    private Long permId;

    private Date createTime;
    private Date updateTime;


}
