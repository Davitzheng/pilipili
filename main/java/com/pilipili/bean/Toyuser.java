package com.pilipili.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 10:33
 */
@Data
public class Toyuser implements Serializable {
    private Integer userid;
    private String username;
    private String pwd;
    private String email;
}
