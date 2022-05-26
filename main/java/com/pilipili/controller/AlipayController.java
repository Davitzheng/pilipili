package com.pilipili.controller;


import com.pilipili.bean.JsonModel;
import com.pilipili.commons.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-20 16:57
 */
//@RestController
@Controller
public class AlipayController {

    @Autowired
    private AlipayUtil alipayUtil;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/create")
    public String create(String id , String price , String title, Model model){
       String pay= alipayUtil.pay(id, price, title);
       model.addAttribute("form",pay);
       return "pay";
    }

    @GetMapping("/return")
    public String returnNotice(){
        return "index";
    }

//    @PostMapping("/notify")
//    public void notifyUrl(String trade_status){
//        System.out.println("订单状态为:"+trade_status);
//    }

}
