package com.qin_kai.punchcard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.qin_kai.punchcard.model.Card;
import com.qin_kai.punchcard.model.Category;
import com.qin_kai.punchcard.model.User;
import com.qin_kai.punchcard.request.SendRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PunchCardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PunchCardActivity";

    public static User user;

    private Card card = new Card();

    private Button confirm;

    private Button myCard;

    private Button square;

    private EditText eTContent;

    private EditText eTFeel;

    private TextView tVLocation;

    public LocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_punch_card);
        locationClient = new LocationClient(getApplicationContext());
        // 注册定位监听器
        locationClient.registerLocationListener(new MyLocationListener());
        initSpinner();
        confirm = findViewById(R.id.confirm);
        myCard = findViewById(R.id.my_card);
        square = findViewById(R.id.square);

        tVLocation = findViewById(R.id.tv_location);
        eTContent = findViewById(R.id.et_card_content);
        eTFeel = findViewById(R.id.et_feel);


        confirm.setOnClickListener(this);
        myCard.setOnClickListener(this);
        square.setOnClickListener(this);

        // 存储所有待申请的权限
        List<String> permissionList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(PunchCardActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(PunchCardActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(PunchCardActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }


        // 一次请求所有权限
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(PunchCardActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
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
        Spinner sp = findViewById(R.id.sp_dropdown);
        // 设置下拉框的标题
        sp.setPrompt("请选择分类");
        // 设置下拉框的数组适配器
        sp.setAdapter(starAdapter);
        // 设置下拉框默认显示第一项
        sp.setSelection(0);
        card.setCategoryId(categoryIdList.get(0));
        Log.d(TAG, "initSpinner: " + card);
        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp.setOnItemSelectedListener(new MySelectedListener());
    }

    private void initCategory() {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    categoryList = SendRequest.getCategoryList();
                    Log.d(TAG, "run: " + categoryList);
                }
            });
            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < categoryList.size(); i++) {
            categoryNameList.add(categoryList.get(i).getCategoryName());
            categoryIdList.add(categoryList.get(i).getCategoryId());
        }
    }

    // 定义下拉列表需要显示的文本数组
    private List<Category> categoryList = new ArrayList<>();
    private List<String> categoryNameList = new ArrayList<>();
    private List<Integer> categoryIdList = new ArrayList<>();


    // 定义一个选择监听器，它实现了接口OnItemSelectedListener
    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        // 选择事件的处理方法，其中arg2代表选择项的序号
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            card.setCategoryId(categoryIdList.get(arg2));
        }

        // 未选择时的处理方法，通常无需关注
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm) {
            card.setContent(eTContent.getText().toString());
            card.setFeel(eTFeel.getText().toString());
            card.setAddress(tVLocation.getText().toString());
            card.setUserId(user.getUserId());

            if (card.getContent().length() == 0) {
                Toast.makeText(PunchCardActivity.this, "打卡内容不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map = new HashMap<>();
                        map = SendRequest.saveCard(card);
                        checkSaveCard(map);
                    }
                });
                t.start();
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (v.getId() == R.id.my_card) {
            Intent intent = new Intent(PunchCardActivity.this, MyCardListActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.square) {
            Intent intent = new Intent(PunchCardActivity.this, CardListActivity.class);
            startActivity(intent);
        }
    }

    private void checkSaveCard(final Map map) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (map.get("code").equals(0)) {
                    eTContent.setText("");
                    eTFeel.setText("");
                    Toast.makeText(PunchCardActivity.this, "打卡成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PunchCardActivity.this, MyCardListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PunchCardActivity.this, map.get("msg").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 初始化LocationClient，设置optiton（刷新时间间隔）
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();

        // 设置刷新时间间隔
        option.setScanSpan(5000);
        // 获取详细的地址信息
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);
    }


    /**
     * 请求获取位置
     */
    private void requestLocation() {
        initLocation();
        locationClient.start();
    }

    /**
     * 位置监听器
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {

            runOnUiThread(new Runnable() {

                // 获取当前位置
                @Override
                public void run() {
                    StringBuilder sb = new StringBuilder();
                    if (bdLocation.getProvince() != null) {
                        sb.append(bdLocation.getProvince()).append(" ");
                        sb.append(bdLocation.getCity()).append(" ");
                        sb.append(bdLocation.getDistrict()).append(" ");
                        sb.append(bdLocation.getStreet());
                        tVLocation.setText(sb.toString());
                    }
                    Log.d(TAG, "run: " + sb.toString());

                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(PunchCardActivity.this, "必须同意所有权限才能使用本程序",
                                    Toast.LENGTH_SHORT).show();

                            // 退出
                            finish();
                            return;
                        }

                        requestLocation();
                    }
                } else {
                    Toast.makeText(PunchCardActivity.this, "发生未知错误",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;

            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
    }
}
