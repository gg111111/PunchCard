package com.android.pcs.mapper;

import com.android.pcs.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

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
    int register(User user);


    /**
     * 检查用户名是否已经存在
     * @param user
     * @return
     */
    int checkUserIsExsited(User user);

}
