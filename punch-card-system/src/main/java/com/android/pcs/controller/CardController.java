package com.android.pcs.controller;

import com.android.pcs.enums.ExceptionEnum;
import com.android.pcs.model.Card;
import com.android.pcs.service.CardService;
import com.android.pcs.util.ResultUtil;
import com.android.pcs.vo.CardVO;
import com.android.pcs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/saveCard")
    public ResultVO saveCard(Card card) {
        int effctNum = cardService.saveCard(card);
        if (effctNum < 1) {
            return ResultUtil.error(ExceptionEnum.PUNCH_ERROR);
        }

        return ResultUtil.success();
    }


    @RequestMapping("/cardList")
    public ResultVO  cardList(Card card) {
        List<CardVO> cardList = cardService.getCardList(card);
        return ResultUtil.success(cardList);
    }

    @RequestMapping("/removeCard")
    public ResultVO removeCard(Integer cardId) {
        cardService.removeCard(cardId);
        return ResultUtil.success();
    }

    @RequestMapping("/updateCard")
    public ResultVO updateCard(Card card) {
        cardService.updateCard(card);
        return  ResultUtil.success();
    }
}
