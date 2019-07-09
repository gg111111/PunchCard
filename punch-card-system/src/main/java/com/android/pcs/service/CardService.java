package com.android.pcs.service;

import com.android.pcs.model.Card;
import com.android.pcs.vo.CardVO;

import java.util.List;

public interface CardService {

    /**
     * 保存打卡信息
     * @param card
     * @return
     */
    int saveCard(Card card);

    /**
     * 删除打卡信息
     * @param cardId
     * @return
     */
    int removeCard(Integer cardId);

    /**
     * 获取打卡列表
     * @param card
     * @return
     */
    List<CardVO> getCardList(Card card);

    /**
     * 修改Card
     * @param card
     * @return
     */
    int updateCard(Card card);

}
