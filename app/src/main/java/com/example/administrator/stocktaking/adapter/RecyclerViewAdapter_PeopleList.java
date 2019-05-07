package com.example.administrator.stocktaking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.stocktaking.R;
import com.example.administrator.stocktaking.ScanQRCodeActivity;
import com.example.administrator.stocktaking.Tool.FileHelper;
import com.example.administrator.stocktaking.bean.ListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/4.
 */


public class RecyclerViewAdapter_PeopleList extends RecyclerView.Adapter<RecyclerViewAdapter_PeopleList.ViewHolder>{
    private List<ListBean> list;
    private Context context;

    public RecyclerViewAdapter_PeopleList(List<ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_list,parent,false);
        RecyclerViewAdapter_PeopleList.ViewHolder viewHolder = new RecyclerViewAdapter_PeopleList.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.button.setText(list.get(position).getName());
        //holder.itemView
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();
                removeItem(position);
                FileHelper.writeTxtToFile(StringGetListString(),FileHelper.MainAddress,"MarksList.txt",false  ,false);
            }
        });
    }

  public String StringGetListString(){
      String result="";
      for (int i = 0; i <list.size() ; i++) {
          result +=list.get(i).getName()+"\r\n";
      }
      return result;
  }


    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
           // txtName = itemView.findViewById(R.id.tv_name);
            button = itemView.findViewById(R.id.tv_name);
        }
    }
}
