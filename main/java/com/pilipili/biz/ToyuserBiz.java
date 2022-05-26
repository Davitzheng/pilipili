package com.pilipili.biz;

import com.pilipili.bean.Toyuser;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 10:54
 */
public interface ToyuserBiz {
    public Toyuser login(String uname, String pwd);
    public int register(String uname, String pwd, String email);
}
