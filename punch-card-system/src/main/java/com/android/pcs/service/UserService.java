package com.android.pcs.service;

import com.android.pcs.exception.MyException;
import com.android.pcs.model.User;

public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    int register(User user) throws MyException;

}
