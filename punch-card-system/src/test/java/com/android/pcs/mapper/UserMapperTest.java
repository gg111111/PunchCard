package com.android.pcs.mapper;

import com.android.pcs.BaseTest;
import com.android.pcs.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void login() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");
        User resUser = userMapper.login(user);
        System.out.println(resUser);
    }

    @Test
    public void register() {
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123");
        int effectNum = userMapper.register(user);
        Assert.assertEquals(1, effectNum);
    }

    @Test
    public void checkUserIsExsited() {
        User user = new User();
        user.setUsername("test");
        int cnt = userMapper.checkUserIsExsited(user);
        Assert.assertEquals(1, cnt);
    }
}