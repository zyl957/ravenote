package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.mapper.UserUnitMapper;

import javax.annotation.Resource;

@Service
public class UserUnitService {

    @Resource
    UserUnitMapper userUnitMapper;

    public void insertUserUnit(String username) {
        userUnitMapper.insertUserUnit(username,"CM50109");
        userUnitMapper.insertUserUnit(username,"CM50262");
    }
}
