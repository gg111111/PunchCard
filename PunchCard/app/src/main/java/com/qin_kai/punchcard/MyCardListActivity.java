package com.qin_kai.punchcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.qin_kai.punchcard.model.Card;
import com.qin_kai.punchcard.model.CardVO;
import com.qin_kai.punchcard.request.SendRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCardListActivity extends AppCompatActivity {

    private List<CardVO> cardList = new ArrayList<>();

    private static final String TAG = "MyCardListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card_list);

        initCard();

        CardAdapter adapter = new CardAdapter(MyCardListActivity.this, R.layout.card_item, cardList);
        final ListView listView = (ListView) findViewById(R.id.my_list_view);
        listView.setAdapter(adapter);

        // ListView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                final CardVO card = cardList.get(position);
                final String items[] = {"修改", "删除"};
                AlertDialog dialog = new AlertDialog.Builder(MyCardListActivity.this)
                        .setIcon(R.mipmap.icon)//设置标题的图片
                        .setTitle("请选择")//设置对话框的标题
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent intent = new Intent(MyCardListActivity.this, UpdateCardActivity.class);
                                    intent.putExtra("card", card);
                                    UpdateCardActivity.activity = MyCardListActivity.this;
                                    startActivity(intent);
                                }
                                if (which == 1) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            final Map<String, Object> map = SendRequest.removeCard(card.getCardId());
                                            checkRemove(map, listView);
                                        }
                                    }).start();

                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });

    }

    private void checkRemove(final Map<String, Object> map, final ListView listView) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (map.get("code").equals(0)) {
                    Toast.makeText(MyCardListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    initCard();
                    listView.setAdapter(new CardAdapter(MyCardListActivity.this,
                            R.layout.card_item, cardList));
                } else {
                    Toast.makeText(MyCardListActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initCard() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Card card = new Card();
                    card.setUserId(PunchCardActivity.user.getUserId());
                    cardList = SendRequest.getCardList(card);
                }
            });
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
