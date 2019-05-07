package com.example.administrator.stocktaking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

   private Button btn_one;
    private  Button btn_two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_one = findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
               ShowActivity(HistoryRecord.class);
                break;
            case R.id.btn_two:
                ShowActivity(PeopleManage.class);
                break;
            default:
                break;
        }
    }

    public  void ShowActivity(Class activityClass){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,activityClass);
        startActivity(intent);
    }
}
