package com.android.pcs.service.impl;

import com.android.pcs.BaseTest;
import com.android.pcs.exception.MyException;
import com.android.pcs.model.User;
import com.android.pcs.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() throws MyException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        userService.register(user);
    }
}