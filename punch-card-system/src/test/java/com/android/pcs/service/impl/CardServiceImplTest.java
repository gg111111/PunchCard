package com.android.pcs.service.impl;

import com.android.pcs.BaseTest;
import com.android.pcs.model.Card;
import com.android.pcs.service.CardService;
import com.android.pcs.vo.CardVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CardServiceImplTest extends BaseTest {

    @Autowired
    private CardService cardService;

    @Test
    public void getCardList() {
        Card card = new Card();
        List<CardVO> cardList = cardService.getCardList(card);
        System.out.println(cardList);
    }
}