package com.uz.music_player.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.uz.music_player.R;
import com.uz.music_player.databinding.ItemUserBinding;
import com.uz.music_player.models.User;
import com.uz.music_player.utils.MyItemTouchHelper;

import java.util.Collections;
import java.util.List;

public class
UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> implements MyItemTouchHelper {


   private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  UserAdapter.MyViewHolder holder, int position) {
        User user = userList.get(position);

        holder.binding.tv1.setText(user.getName());
        holder.binding.tv2.setText(user.getPassword());
        Animation animation= AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation3);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition<toPosition){
            for (int i = fromPosition; i <toPosition; i++) {
                Collections.swap(userList,i,i+1);
            }
        }else{
            for (int i = toPosition; i >=fromPosition; i--) {
                Collections.swap(userList,i,i-1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        userList.remove(position);
        notifyItemRemoved(position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ItemUserBinding binding;
        public MyViewHolder(@NonNull  ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
