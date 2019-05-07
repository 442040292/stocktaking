package com.example.administrator.stocktaking;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.stocktaking.R;
import com.example.administrator.stocktaking.adapter.RecyclerViewAdapter_HistoryList;
import com.example.administrator.stocktaking.bean.ListBean;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;




public class Stocktake extends Activity implements View.OnClickListener{
    private RecyclerView revyclerView;
    private RecyclerViewAdapter_HistoryList adapter;
    private List<ListBean> list;

    private Button openscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocktake);
        openscan = findViewById(R.id.openscan);
        openscan.setOnClickListener(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openscan:
               // list.add(new ListBean(list.size()+1,"shu ju"+list.size()+1));
                new IntentIntegrator(this).initiateScan();
              //  revyclerView.setAdapter(adapter);
                break;

            default:
                break;
        }
    }
    private void initView() {
        //revyclerView = (RecyclerView) findViewById(R.id.revyclerView);

        list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add(new ListBean(i,"shu ju"+i));
        }

        //全部客服
      //  LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(Stocktake.this);
      //  linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
       // revyclerView.setLayoutManager(linearLayoutManager3);
       // adapter = new RecyclerViewAdapter_HistoryList(list,Stocktake.this);
      //  revyclerView.setAdapter(adapter);
    }
}