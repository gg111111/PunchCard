package com.android.pcs.mapper;

import com.android.pcs.BaseTest;
import com.android.pcs.model.Card;
import com.android.pcs.vo.CardVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CardMapperTest extends BaseTest {

    @Autowired
    private CardMapper cardMapper;

    @Test
    public void saveCard() {
        Card card = new Card();
        card.setUserId(1);
        card.setAddress("test");
        card.setContent("test");
        card.setCreateTime(new Date());
        card.setCategoryId(1);
        int effectNum = cardMapper.saveCard(card);
        assertEquals(1, effectNum);
    }

    @Test
    public void getCardList() {
        Card card = new Card();
        card.setCategoryId(1);
        List<CardVO> cardList = cardMapper.getCardList(card);
        System.out.println(cardList);
    }

    @Test
    public void removeCard() {
        int effectNum = cardMapper.removeCard(22);
        Assert.assertEquals(1, effectNum);
    }

    @Test
    public void updateCard() {
        Card card = new Card();
        card.setCardId(21);
        card.setContent("游泳1小时");
        card.setFeel("好累。。好累");
        card.setCategoryId(3);
        int effectNum = cardMapper.updateCard(card);
        Assert.assertEquals(1, effectNum);
    }
}