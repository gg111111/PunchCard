package com.android.pcs.util;

import com.android.pcs.enums.ExceptionEnum;
import com.android.pcs.vo.ResultVO;

public class ResultUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(ExceptionEnum exceptionEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(exceptionEnum.getCode());
        resultVO.setMsg(exceptionEnum.getMsg());
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
