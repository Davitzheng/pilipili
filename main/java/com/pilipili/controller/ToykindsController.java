package com.pilipili.controller;

import com.pilipili.bean.JsonModel;
import com.pilipili.bean.Toykinds;
import com.pilipili.biz.ToykindsBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ToykindsController {
    private Logger logging = LoggerFactory.getLogger(com.pilipili.controller.ToykindsController.class);
    @Autowired
    private ToykindsBiz toykindsBiz;
    @RequestMapping("/findAllKinds")
    public JsonModel findAll(HttpSession session){
        JsonModel jm = new JsonModel();
        try{
            List<Toykinds> list=toykindsBiz.findAll();
            jm.setCode(1);
            jm.setData(list);
            session.setAttribute("list",list);
        }catch (Exception ex){
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }
        return jm;
    }
}
