package com.android.pcs.mapper;

import com.android.pcs.model.Card;
import com.android.pcs.vo.CardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardMapper {

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
     * 修改card
     * @param card
     * @return
     */
    int updateCard(Card card);
}
