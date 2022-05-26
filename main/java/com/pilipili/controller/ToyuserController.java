package com.pilipili.controller;

import com.pilipili.bean.JsonModel;
import com.pilipili.bean.Toyuser;
import com.pilipili.biz.ToyuserBiz;
import com.pilipili.commons.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ToyuserController {
    @Autowired
    private ToyuserBiz toyuserBiz;

    @RequestMapping("/login")
    public JsonModel login(String username,String pwd,String valcode,HttpSession session){
        JsonModel jm=new JsonModel();
        try {
            String validateCode = session.getAttribute("validateCode").toString();
            if (!validateCode.equalsIgnoreCase(valcode)) {
                jm.setCode(0);
                jm.setMsg("验证不正确，请确认后重新输入");
                return jm;
            }
            Toyuser user = toyuserBiz.login(username,pwd);
            if (user!=null){
                session.setAttribute(DataDict.TOYUSER,user);
                user.setPwd("");
                jm.setCode(1);
                jm.setData(user);
            }else{
                jm.setCode(0);
                jm.setMsg("用户名或密码错误");
            }
            return jm;
        }catch(Exception ex){
            jm.setCode(0);
            jm.setMsg(   ex.getMessage() );
        }
        //3. 回送信息
       return jm;
    }

    @RequestMapping("/logout")
    public JsonModel logout(HttpSession session){
        JsonModel jm=new JsonModel();

        session.removeAttribute(DataDict.TOYUSER);

        jm.setCode(1);
        return jm;
    }

    @RequestMapping("/checkLogin")
    public JsonModel checkLogin(HttpSession session){
        JsonModel jm=new JsonModel();
        if(   session.getAttribute(DataDict.TOYUSER)!=null){
            jm.setCode(1);
            Toyuser resuser=(Toyuser) session.getAttribute(DataDict.TOYUSER);
            resuser.setPwd("");
            jm.setData( resuser   );
        }else{
            jm.setCode(0);
        }
       return  jm;
    }

    @RequestMapping("/register")
    public JsonModel register(String username,String pwd,String email){
        JsonModel jm=new JsonModel();
        try{
            int a = toyuserBiz.register(username,pwd,email);
            if (a==1){
                jm.setCode(1);
            }else{
                jm.setCode(0);
                jm.setMsg("注册失败");
            }
            return jm;
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(   ex.getMessage() );
        }
        return jm;
    }

}
