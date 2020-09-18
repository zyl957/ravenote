package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.mapper.UserMapper;
import org.zhaoyangli.ravenote.model.User;
import org.zhaoyangli.ravenote.model.UserAccount;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null){
            throw new CustomException(CustomErrorCodeEnum.USER_NOT_FOUND);
        }
        return user;
    }

    // Check if current user has the access to visit the unit
    public void userValidationCheck(User user, UserAccount userAccount){
        if(!user.getUsername().equals(userAccount.getUsername())){
            throw new CustomException(CustomErrorCodeEnum.HAVE_NO_ACCESS);
        }
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void insertNewUser(User user) {
        userMapper.insertNewUser(user);
    }
}
