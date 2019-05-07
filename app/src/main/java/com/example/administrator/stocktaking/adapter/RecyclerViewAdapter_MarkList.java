package com.example.administrator.stocktaking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.stocktaking.R;
import com.example.administrator.stocktaking.Tool.FileHelper;
import com.example.administrator.stocktaking.bean.ListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/6.
 */


public class RecyclerViewAdapter_MarkList extends RecyclerView.Adapter<RecyclerViewAdapter_MarkList.ViewHolder>{
    private List<ListBean> list;
    private Context context;

    public RecyclerViewAdapter_MarkList(List<ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_list,parent,false);
        RecyclerViewAdapter_MarkList.ViewHolder viewHolder = new RecyclerViewAdapter_MarkList.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.button.setText(list.get(position).getName());
        //holder.itemView
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();
               // removeItem(position);
                onItemClickListener.OnItemClick(v,holder,position);
                notifyDataSetChanged();
               // FileHelper.writeTxtToFile(StringGetListString(),FileHelper.MainAddress,"MarksList.txt",false  ,false);
            }
        });
    }

    public String StringGetListString(){
        String result="";
        for (int i = 0; i <list.size() ; i++) {
            result +=list.get(i)+"\r\n";
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

    private RecyclerViewAdapter_MarkList.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {  //定义接口，实现Recyclerview点击事件
        void OnItemClick(View view, RecyclerViewAdapter_MarkList.ViewHolder holder, int position);
    }
    public void setOnItemClickListener(RecyclerViewAdapter_MarkList.OnItemClickListener onItemClickListener) {   //实现点击
        this.onItemClickListener = onItemClickListener;
    }
}
