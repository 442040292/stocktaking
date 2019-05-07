package com.example.administrator.stocktaking;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.stocktaking.Tool.FileHelper;
import com.example.administrator.stocktaking.adapter.RecycleerViewAdapter;
import com.example.administrator.stocktaking.adapter.RecyclerViewAdapter_HistoryList;
import com.example.administrator.stocktaking.bean.ListBean;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecord extends Activity implements View.OnClickListener{
    private RecyclerView revyclerView;
    private RecyclerViewAdapter_HistoryList adapter;
    private List<ListBean> list;

    private EditText editText;
    private Button addhistoryButton1;
    private Button addMarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_record);
        addhistoryButton1 = findViewById(R.id.addhistoryButton);
        addhistoryButton1.setOnClickListener(this);
        editText=findViewById(R.id.recordName);
        addMarkButton=findViewById(R.id.addMarkButton);
        addMarkButton.setOnClickListener(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addhistoryButton:
                list.add(new ListBean(list.size()+1,editText.getText().toString()+".txt"));
               // FileHelper.writeTxtToFile("",FileHelper.Recordaddress,editText.getText().toString()+".txt",false,false);
                revyclerView.setAdapter(adapter);
                break;
            case R.id.addMarkButton:
                ShowActivity(PeopleManage.class);
                break;


            default:
                break;
        }
    }

    private void initView() {
        revyclerView = (RecyclerView) findViewById(R.id.revyclerView);
        List<String>  temp= FileHelper.GetFiles(FileHelper.Recordaddress);
        list = new ArrayList<>();
        for (int i = 0; i <temp.size() ; i++) {
            list.add(new ListBean(i,temp.get(i)));
        }

        //全部客服
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(HistoryRecord.this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        revyclerView.setLayoutManager(linearLayoutManager3);
        adapter = new RecyclerViewAdapter_HistoryList(list,HistoryRecord.this);
        revyclerView.setAdapter(adapter);
    }

    public  void ShowActivity(Class activityClass){
        Intent intent = new Intent();
        intent.setClass(HistoryRecord.this,activityClass);
        startActivity(intent);
    }
}
