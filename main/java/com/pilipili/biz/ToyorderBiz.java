package com.pilipili.biz;

import com.pilipili.bean.CartItem;
import com.pilipili.bean.Toyorder;

import java.util.Map;

public interface ToyorderBiz {
    public void confirm(Toyorder order, Map<Integer, CartItem> cart);
}
