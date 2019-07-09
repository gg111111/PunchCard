package com.android.pcs.service.impl;

import com.android.pcs.mapper.CardMapper;
import com.android.pcs.model.Card;
import com.android.pcs.service.CardService;
import com.android.pcs.vo.CardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public int saveCard(Card card) {
        return cardMapper.saveCard(card);
    }

    @Override
    public int removeCard(Integer cardId) {
        return cardMapper.removeCard(cardId);
    }

    @Override
    public List<CardVO> getCardList(Card card) {
        return cardMapper.getCardList(card);
    }

    @Override
    public int updateCard(Card card) {
        return cardMapper.updateCard(card);
    }
}
