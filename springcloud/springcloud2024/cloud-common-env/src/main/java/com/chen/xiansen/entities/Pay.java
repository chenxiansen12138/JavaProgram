package com.chen.xiansen.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 表名：t_pay
 * 表注释：支付交易表
*/
@Getter
@Setter
@Table(name = "t_pay")
public class Pay {
    /**
     * -- GETTER --
     *
     * @return id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 支付流水号
     * -- GETTER --
     *  获取支付流水号
     *
     * @return payNo - 支付流水号

     */
    @Column(name = "pay_no")
    private String payNo;

    /**
     * 订单流水号
     * -- GETTER --
     *  获取订单流水号
     *
     * @return orderNo - 订单流水号

     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户账号ID
     * -- GETTER --
     *  获取用户账号ID
     *
     * @return userId - 用户账号ID

     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 交易金额
     * -- GETTER --
     *  获取交易金额
     *
     * @return amount - 交易金额

     */
    private BigDecimal amount;

    /**
     * 删除标志，默认0不删除，1删除
     * -- GETTER --
     *  获取删除标志，默认0不删除，1删除
     *
     * @return deleted - 删除标志，默认0不删除，1删除

     */
    private Byte deleted;

    /**
     * 创建时间
     * -- GETTER --
     *  获取创建时间
     *
     * @return createTime - 创建时间

     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime createTime;

    /**
     * 更新时间
     * -- GETTER --
     *  获取更新时间
     *
     * @return updateTime - 更新时间

     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime updateTime;
}