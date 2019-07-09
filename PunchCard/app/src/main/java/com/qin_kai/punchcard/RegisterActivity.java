package com.qin_kai.punchcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qin_kai.punchcard.model.User;
import com.qin_kai.punchcard.request.SendRequest;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;

    private EditText username;

    private EditText password;

    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.reg_confirm);
        register.setOnClickListener(this);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reg_confirm) {
            if (username.getText().length() == 0 || password.getText().length() == 0) {
                Toast.makeText(RegisterActivity.this, "账号或密码不能为空!", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    Map map = SendRequest.register(user);
                    checkRegister(map);
                }
            }).start();
        }
    }

    private void checkRegister(final Map map) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Integer code = (Integer) map.get("code");
                if (code.equals(0)) {
                    Toast.makeText(RegisterActivity.this, "注册成功，请登录！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, map.get("msg").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
