package com.pilipili.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 10:39
 */
@Data
public class Toykinds implements Serializable {

    private Integer tid;
    private String tname;
    private Double total;
    private Double earnest;
    private String detail;
    private String tphoto;

}
