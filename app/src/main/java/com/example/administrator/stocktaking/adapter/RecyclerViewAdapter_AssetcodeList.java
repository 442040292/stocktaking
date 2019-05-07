package com.example.administrator.stocktaking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.stocktaking.R;
import com.example.administrator.stocktaking.bean.ListBean;
import com.example.administrator.stocktaking.bean.ListBean_Assetcode;

import java.util.List;

/**
 * Created by Administrator on 2019/5/4.
 */

public class RecyclerViewAdapter_AssetcodeList extends RecyclerView.Adapter<RecyclerViewAdapter_AssetcodeList.ViewHolder>{
    public List<ListBean_Assetcode> list;
    private Context context;

    public  int CurrentPosition=0;

    public RecyclerViewAdapter_AssetcodeList(List<ListBean_Assetcode> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_list,parent,false);
        RecyclerViewAdapter_AssetcodeList.ViewHolder viewHolder = new RecyclerViewAdapter_AssetcodeList.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtName.setText(list.get(position).getCode()+"_"+list.get(position).getMark());
        ListBean_Assetcode listBean = list.get(position);
        holder.itemView.setBackgroundColor(Color.YELLOW);
        ///////////////////////////////////點擊變色/////////////////////////////////////////////
        if (position == CurrentPosition) {
            //選中的顔色就設成了  黃色
            holder.itemView.setBackgroundResource(R.color.colorLiteBlue);
        } else {
            //未選中的顔色 就設成了 白色
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        ////////////////////////////////////點擊變色////////////////////////////////////////////
        holder.deleteButton.setOnClickListener(
             new View.OnClickListener(){
                 @Override
                 public void onClick(View v) {
                    list.remove(position);
                     notifyItemRemoved(position);
                     notifyDataSetChanged();
                 }
             }
        );
        holder.selectButton.setOnClickListener(
                new View.OnClickListener(   ){
                    @Override
                    public void onClick(View v) {
                     //   onItemClickListener.OnItemClick(v,holder,position);
                        CurrentPosition=position;
                        notifyDataSetChanged();
                    }
                }
        );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onItemClickListener.OnItemClick(v,holder,position);
                CurrentPosition=position;
                notifyDataSetChanged();
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void  setMark(String Mark){
        list.get(CurrentPosition).setMark(Mark);
        notifyDataSetChanged();
    }

    private  void SetSelectBackground(ViewHolder holder,int position){
        if (  position==CurrentPosition  ){
            holder.itemView.setBackgroundResource(R.color.colorLiteBlue);
        }else {
            holder.itemView.setBackgroundResource(R.color.colorWhit);
        }
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
        public Button selectButton;
        public TextView deleteButton;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textView);
            selectButton = itemView.findViewById(R.id.selectButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }



}