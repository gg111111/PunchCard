package com.android.pcs.service.impl;

import com.android.pcs.exception.MyException;
import com.android.pcs.mapper.UserMapper;
import com.android.pcs.model.User;
import com.android.pcs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int register(User user) throws MyException {
        int cnt = userMapper.checkUserIsExsited(user);

        // 账号存在
        if (cnt > 0) {
            throw new MyException("账号已经存在");
        }
        return userMapper.register(user);
    }
}
