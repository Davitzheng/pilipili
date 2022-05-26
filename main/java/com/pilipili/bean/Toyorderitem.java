package com.pilipili.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 10:35
 */
@Data
public class Toyorderitem implements Serializable {
    private Integer toiid ;
    private Integer toid ;
    private Integer tid ;
    private Double dealprice ;
    private Integer num ;
}
