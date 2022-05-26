package com.pilipili.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pilipili.bean.Toyuser;
import com.pilipili.commons.Encrypt;
import com.pilipili.mapper.ToyuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hasee
 */
@Service
public class ToyuserBizImpl implements ToyuserBiz{

    @Autowired
    private ToyuserMapper toyuserMapper;

    @Override
    public Toyuser login(String username, String pwd) {
        pwd= Encrypt.md5(pwd);
        QueryWrapper<Toyuser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("pwd",pwd);
        return toyuserMapper.selectOne(wrapper);
    }

    @Override
    public int register(String uname, String pwd, String email) {
        pwd= Encrypt.md5(pwd);
        Toyuser  toyuser = new Toyuser();
        toyuser.setUsername(uname);
        toyuser.setPwd(pwd);
        toyuser.setEmail(email);
        return toyuserMapper.insert(toyuser);
    }
}
