package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.mapper.UserAccountMapper;
import org.zhaoyangli.ravenote.model.UserAccount;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    public String getPasswordByUsername(String inputUsername) {
        String password = userAccountMapper.getPasswordByUsername(inputUsername);
//        if(password == null){
//            throw new CustomException(CustomErrorCodeEnum.USER_NOT_FOUND);
//        }
        return password;
    }

    public void updateCookie(String token, String inputUsername) {
        userAccountMapper.updateCookie(token,inputUsername);
    }

    public List<String> getUsernames() {
        return userAccountMapper.getUsernames();
    }

    public void insertNewAccount(UserAccount userAccount) {
        userAccountMapper.insertNewAccount(userAccount);
    }

    public UserAccount getUserAccountByUsername(String inputUsername) {
        return userAccountMapper.getUserAccountByUsername(inputUsername);
    }
}
