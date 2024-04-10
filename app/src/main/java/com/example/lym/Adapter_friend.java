package com.example.lym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lym.Model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_friend extends RecyclerView.Adapter<Adapter_friend.friendViewHolder> {

    List<User> mylist;

    public void setList(List<User> mList) {
        this.mylist = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public friendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new friendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull friendViewHolder holder, int position) {
        if (mylist != null) {
            User tmpUser = mylist.get(position);
            holder.tv_name.setText(tmpUser.name);
            Picasso.get().load(tmpUser.avatar).into(holder.imageview);
            holder.imageview.setImageResource(R.drawable.baseline_dangerous_24);
        }
    }
    @Override
    public int getItemCount() {
        if (mylist != null)
            return mylist.size();
        else return 0;
    }

    public static class friendViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_name;
        public ImageView imageview;
        public friendViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tvName);
            imageview = itemView.findViewById(R.id.ivImage);
//            tv_timekhoanchi = itemView.findViewById(R.id.tv_ngaychi);
//            Tv_tenkhoanchi = itemView.findViewById(R.id.tv_tenkhoanchi);
//            imageviewCT = itemView.findViewById(R.id.imgseen);
//            imageviewedit = itemView.findViewById(R.id.imgedit);
        }
    }


}
