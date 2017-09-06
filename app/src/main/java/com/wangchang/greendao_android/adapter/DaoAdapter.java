package com.wangchang.greendao_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangchang.greendao_android.R;
import com.wangchang.greendao_android.db.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class DaoAdapter extends RecyclerView.Adapter<DaoAdapter.ViewHolder> {

    private List<User> userList=new ArrayList<>();

    public void addData(List<User> list){
        if(list!=null&&list.size()>0){
            userList.clear();
            userList.addAll(list);
            notifyDataSetChanged();
        }
    }
    @Override
    public DaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(DaoAdapter.ViewHolder holder, int position) {
        User user=userList.get(position);
        holder.id.setText(user.getId()+"");
        holder.age.setText(user.getAge()+"");
        holder.desc.setText(user.getDesc());
        holder.sex.setText(user.getSex());
        holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id,name,age,sex,desc;
        public ViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.tv_id);
            name=itemView.findViewById(R.id.tv_name);
            age=itemView.findViewById(R.id.tv_age);
            sex=itemView.findViewById(R.id.tv_sex);
            desc=itemView.findViewById(R.id.tv_desc);
        }
    }
}
