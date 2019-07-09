package com.qin_kai.punchcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.qin_kai.punchcard.model.Card;
import com.qin_kai.punchcard.model.CardVO;
import com.qin_kai.punchcard.model.Category;
import com.qin_kai.punchcard.request.SendRequest;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends AppCompatActivity {

    private List<CardVO> cardList = new ArrayList<>();

    private static final String TAG = "CardListActivity";

    private Card card = new Card();

    private CardAdapter adapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        initSpinner();
        initCard();

        adapter = new CardAdapter(CardListActivity.this, R.layout.card_item, cardList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // ListView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CardVO card = cardList.get(position);
                Toast.makeText(CardListActivity.this, card.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 初始化下拉框
    private void initSpinner() {
        initCategory();

        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, categoryNameList);
        // 设置数组适配器的布局样式
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        // 从布局文件中获取名叫sp_dialog的下拉框
        Spinner sp = findViewById(R.id.sp_dropdown_list);
        // 设置下拉框的标题
        sp.setPrompt("请选择分类");
        // 设置下拉框的数组适配器
        sp.setAdapter(starAdapter);
        // 设置下拉框默认显示第一项
        sp.setSelection(0);
        card.setCategoryId(categoryIdList.get(0));
        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp.setOnItemSelectedListener(new MySelectedListener());
    }


    // 定义一个选择监听器，它实现了接口OnItemSelectedListener
    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        // 选择事件的处理方法，其中arg2代表选择项的序号
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            card.setCategoryId(categoryIdList.get(arg2));
            initCard();
            adapter = new CardAdapter(CardListActivity.this, R.layout.card_item, cardList);
            listView.setAdapter(adapter);
        }

        // 未选择时的处理方法，通常无需关注
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    private void initCategory() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    categoryList = SendRequest.getCategoryList();
                }
            });
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        categoryNameList.clear();
        categoryIdList.clear();
        for (int i = 0; i < categoryList.size(); i++) {
            categoryNameList.add(categoryList.get(i).getCategoryName());
            categoryIdList.add(categoryList.get(i).getCategoryId());
        }
    }

    // 定义下拉列表需要显示的文本数组
    private List<Category> categoryList = new ArrayList<>();
    private List<String> categoryNameList = new ArrayList<>();
    private List<Integer> categoryIdList = new ArrayList<>();


    private void initCard() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
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
