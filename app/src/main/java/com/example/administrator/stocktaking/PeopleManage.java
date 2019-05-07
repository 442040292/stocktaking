package com.example.administrator.stocktaking;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.stocktaking.Tool.FileHelper;
import com.example.administrator.stocktaking.adapter.RecyclerViewAdapter_PeopleList;
import com.example.administrator.stocktaking.bean.ListBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PeopleManage extends Activity implements View.OnClickListener{
    private RecyclerView revyclerView;
    private RecyclerViewAdapter_PeopleList adapter;
    private List<ListBean> list;
    private EditText peopleName;

    private Button addpeopleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_manage);
        addpeopleButton = findViewById(R.id.addpeopleButton);
        addpeopleButton.setOnClickListener(this);
        initView();
    }
    private void initView() {
        revyclerView = (RecyclerView) findViewById(R.id.revyclerView);
        peopleName=findViewById(R.id.peopleName);

        list = new ArrayList<>();
        if ( new File(FileHelper.MarksListdaddress).exists())
        {
         List<String>  marksList= FileHelper.ReadAlines(FileHelper.MarksListdaddress);
            for (int i = 0; i <marksList.size() ; i++) {
                list.add(new ListBean(i,marksList.get(i)));
            }
        }


        //LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(PeopleManage.this);
        //linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        //revyclerView.setLayoutManager(linearLayoutManager3);
        revyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        // revyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecyclerViewAdapter_PeopleList(list,PeopleManage.this);
        revyclerView.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addpeopleButton:{
                String _name=peopleName.getText().toString();
                if (_name.equals("")){
                    Toast.makeText(this, "请输入标签", Toast.LENGTH_SHORT).show();
                }
                peopleName.getText();
                list.add(new ListBean(list.size()+1,_name));
                revyclerView.setAdapter(adapter);
                FileHelper.writeTxtToFile(_name,FileHelper.MainAddress,"MarksList.txt",true,true);
            }
                break;

            default:
                break;
        }
    }

}