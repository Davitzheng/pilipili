package com.pilipili.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 10:37
 */
@Data
public class Toyorder implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer toid;
    private Integer userid;
    private String address;
    private String tel;

    private String ordertime;
    private String deliverytime;

    private String ps;
    private Integer status=0;
}
