package com.pilipili.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 购物车项
 */
@Data
public class CartItem implements Serializable {
    private Toykinds toykinds;
    private Integer num;

    //下面的小计只是一个计算的方法，     gson包生成json时，只对属性生成json字符串，不会对方法生成，所以要增加一个属性
    private double smallCount;

    /*
    增加了一个业务处理方法，它没有对应一个属性，完全是计算出来的.   -> vue计算属性
     */
    public double getSmallCount(){
        if(  toykinds==null ){
            return 0;
        }
        smallCount= toykinds.getEarnest()*num;
        return smallCount;
    }


}
