package com.pilipili.biz;

import com.pilipili.bean.Toykinds;
import com.pilipili.mapper.ToykindsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: pilipili
 * @description:
 * @author: 作者
 * @create: 2022-05-10 11:04
 */
@Service
@Transactional(readOnly = true)
public class ToykindsBizImpl implements ToykindsBiz{

    @Autowired
    private ToykindsMapper toykindsMapper;

    @Override
    public List<Toykinds> findAll() {
        return toykindsMapper.selectList(null);
    }
}
