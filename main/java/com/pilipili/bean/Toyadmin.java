package com.pilipili.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 10:44
 */
@Data
public class Toyadmin implements Serializable {

    private int taid;
    private String taname;
    private String tapwd;


}
