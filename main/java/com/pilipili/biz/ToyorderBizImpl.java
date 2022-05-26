package com.pilipili.biz;

import com.pilipili.bean.CartItem;
import com.pilipili.bean.Toykinds;
import com.pilipili.bean.Toyorder;
import com.pilipili.bean.Toyorderitem;
import com.pilipili.mapper.ToyorderMapper;
import com.pilipili.mapper.ToyorderitemMapper;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 19:55
 */
@Service
public class ToyorderBizImpl implements ToyorderBiz{

    @Autowired
    private ToyorderMapper toyorderMapper;

    @Autowired
    private ToyorderitemMapper toyorderitemMapper;
    
    @Override
    public void confirm(Toyorder order, Map<Integer, CartItem> cart) {
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        order.setOrdertime(df.format(d));
        try {
            String s = order.getDeliverytime();
            s = s.substring(4,15);
            DateFormat df2 = new SimpleDateFormat("MMM dd yyyy",Locale.ENGLISH);
            Date d2 = df2.parse(s);
            s=d2.toString();
            System.out.println(s);
            order.setDeliverytime(df.format(d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        toyorderMapper.insert(order);
        int oid = order.getToid();
        for (Map.Entry<Integer,CartItem> entry:cart.entrySet()){
            int tid = entry.getKey();
            CartItem ci = entry.getValue();
            Toykinds toykinds = ci.getToykinds();
            int num=ci.getNum();
            double smallCount =ci.getSmallCount();
            Toyorderitem ri =new Toyorderitem();
            ri.setTid(tid);
            ri.setNum(num);
            ri.setToid(oid);
            ri.setDealprice(toykinds.getEarnest());
        }
    }
}
