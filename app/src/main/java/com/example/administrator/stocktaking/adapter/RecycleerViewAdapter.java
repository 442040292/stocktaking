package com.example.administrator.stocktaking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.stocktaking.R;
import com.example.administrator.stocktaking.bean.ListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/4.
 */

public class RecycleerViewAdapter extends RecyclerView.Adapter<RecycleerViewAdapter.ViewHolder>{
    private List<ListBean> list;
    private Context context;

    public RecycleerViewAdapter(List<ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        RecycleerViewAdapter.ViewHolder viewHolder = new RecycleerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText("xiao le shi sha bi");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
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

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_name);
        }
    }
}
