package com.android.pcs.controller;

import com.android.pcs.enums.ExceptionEnum;
import com.android.pcs.exception.MyException;
import com.android.pcs.model.User;
import com.android.pcs.service.UserService;
import com.android.pcs.util.ResultUtil;
import com.android.pcs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResultVO login(User user) {
        User resUser = userService.login(user);
        if (resUser == null) {
            return ResultUtil.error(ExceptionEnum.USERNMAE_OR_PASSWORD_ERROR);
        }

        return ResultUtil.success(resUser);
    }

    @RequestMapping("/register")
    public ResultVO register(User user) {
        try {
            userService.register(user);
        } catch (MyException e) {
            return ResultUtil.error(ExceptionEnum.USERNAME_IS_EXIST);
        }

        return ResultUtil.success();
    }

}
