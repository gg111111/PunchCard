package com.qin_kai.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qin_kai.punchcard.model.Card;
import com.qin_kai.punchcard.model.CardVO;
import com.qin_kai.punchcard.request.SendRequest;

import java.util.Map;

public class UpdateCardActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView category;

    private TextView createTime;

    private TextView address;

    private TextView content;

    private TextView feel;

    private Button confirm;

    final Card card = new Card();

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);

        category = findViewById(R.id.tv_update_category);
        createTime = findViewById(R.id.tv_update_time);
        address = findViewById(R.id.tv_update_location);
        content = findViewById(R.id.et_update_content);
        feel = findViewById(R.id.et_update_feel);
        confirm = findViewById(R.id.update_confirm);

        confirm.setOnClickListener(this);

        Intent intent = getIntent();
        CardVO c = (CardVO) intent.getSerializableExtra("card");
        card.setCardId(c.getCardId());

        category.setText(c.getCategoryName());
        createTime.setText(CardAdapter.sdf.format(c.getCreateTime()));
        address.setText(c.getAddress());
        content.setText(c.getContent());
        feel.setText(c.getFeel());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.update_confirm) {
            card.setContent(content.getText().toString());
            card.setFeel(feel.getText().toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Map<String, Object> map = SendRequest.updateCard(card);
                    checkUpdate(map);
                }
            }).start();
        }
    }

    private void checkUpdate(final Map<String, Object> map) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(map.get("code").equals(0)) {
                    Toast.makeText(UpdateCardActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateCardActivity.this, MyCardListActivity.class);
                    startActivity(intent);
                    activity.finish();
                    finish();
                } else {
                    Toast.makeText(UpdateCardActivity.this, "修改失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
