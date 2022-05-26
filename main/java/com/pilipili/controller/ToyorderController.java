package com.pilipili.controller;

import com.pilipili.bean.*;
import com.pilipili.biz.ToyorderBiz;
import com.pilipili.commons.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ToyorderController {
    @Autowired
    private ToyorderBiz toyorderBiz;

    @RequestMapping("/confirmOrder")
    public JsonModel confirmOrder(Toyorder toyorder, HttpSession session){
        JsonModel jm = new JsonModel();

        try {

            Toyuser toyuser = (Toyuser) session.getAttribute(DataDict.TOYUSER);

            toyorder.setUserid(toyuser.getUserid());
            toyorder.setStatus(DataDict.ORDER_STATUS_ORDERED);

            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute(DataDict.CART);

            toyorderBiz.confirm(toyorder,cart);
            session.removeAttribute(DataDict.CART);
            jm.setCode(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }
        return jm;
    }

    @RequestMapping("/order")
    public JsonModel order(HttpSession session,int tid,int num){
        JsonModel jm = new JsonModel();
        try {
            if (session.getAttribute(DataDict.TOYUSER) == null) {
                jm.setCode(-1);

                return jm;
            }
            List<Toykinds> list = (List<Toykinds>) session.getAttribute("list");
            Toykinds toykinds=null;
            boolean isFound=false;
            for (Toykinds rf : list){
                if(rf.getTid()==tid){
                    toykinds = rf;
                    isFound=true;
                    break;
                }
            }

            if (isFound==false) {
                jm.setCode(0);
                jm.setMsg("查无此商品," + tid);
                return jm;
            }
            Map<Integer, CartItem> cart = null;
            if (session.getAttribute(DataDict.CART) != null) {
                cart = (Map<Integer, CartItem>) session.getAttribute(DataDict.CART);
            } else {
                cart = new ConcurrentHashMap<>();
            }

            CartItem ci = null;
            if (cart.containsKey(tid)) {
                ci = cart.get(tid);
                ci.setNum(ci.getNum() + num);
            } else {
                ci = new CartItem();
                ci.setToykinds(toykinds);
                ci.setNum(num);
            }
            //计算小计
            ci.getSmallCount();
            cart.put(tid, ci);
            if (ci.getNum() <= 0) {
                cart.remove(tid);
            }

            //将cart购物车存入到 session中
            session.setAttribute(DataDict.CART, cart);

            //添加成功, 回送  code=1
            jm.setCode(1);
            jm.setData(cart);
        } catch (Exception ex) {
            ex.printStackTrace();
            jm.setCode(0);
            jm.setMsg(ex.getMessage());
        }

        return jm;
    }

    @RequestMapping("/clearAll")
    public JsonModel clearAll(HttpSession session){
        JsonModel jm = new JsonModel();
        if (session.getAttribute(DataDict.TOYUSER) == null) {
            jm.setCode(-1);
            return jm;
        }
        session.removeAttribute(DataDict.CART);
        jm.setCode(1);
        return jm;
    }

    @RequestMapping("/getCartInfo")
    public JsonModel getCartInfo(HttpSession session){
        JsonModel jm = new JsonModel();
        if (session.getAttribute(DataDict.TOYUSER) == null) {
            jm.setCode(-1);
            return jm;
        }
        Map<Integer, CartItem> cart= (Map<Integer, CartItem>) session.getAttribute(DataDict.CART);
        jm.setCode(1);
        jm.setData(cart);
        return jm;
    }
}
