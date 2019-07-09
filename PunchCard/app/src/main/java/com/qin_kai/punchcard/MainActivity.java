package com.qin_kai.punchcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.qin_kai.punchcard.model.Card;
import com.qin_kai.punchcard.model.User;
import com.qin_kai.punchcard.request.SendRequest;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";

    private Button login;

    private Button register;

    private EditText usernameEdit;

    private EditText passwordEdit;

    private CheckBox rememberPass;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = findViewById(R.id.remember_pass);
        usernameEdit = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String username = pref.getString("username", "");
            String password = pref.getString("password", "");
            usernameEdit.setText(username);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    public void checkLogin(final Map<String, Object> map) {
        final Integer code = (Integer) map.get("code");
        final String msg = (String) map.get("msg");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (code.equals(0)) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("username", usernameEdit.getText().toString());
                        editor.putString("password", passwordEdit.getText().toString());
                    } else {
                        editor.clear();
                    }

                    editor.apply();
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    PunchCardActivity.user = (User) map.get("user");
                    Intent intent = new Intent(MainActivity.this, PunchCardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(final View v) {
        final User user = new User();
        Card card = new Card();
        try {
            switch (v.getId()) {
                case R.id.login:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            user.setUsername(usernameEdit.getText().toString());
                            user.setPassword(passwordEdit.getText().toString());
                            Map<String, Object> map = SendRequest.login(user);
                            checkLogin(map);
                        }
                    }).start();
                    break;
                case R.id.register:
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                default:
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}