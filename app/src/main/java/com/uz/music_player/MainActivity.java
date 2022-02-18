package com.uz.music_player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.uz.music_player.adapters.UserAdapter;
import com.uz.music_player.databinding.ActivityMainBinding;
import com.uz.music_player.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private List<User> userList;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadUsers();
        userAdapter=new UserAdapter(userList);
        binding.rv.setAdapter(userAdapter);

        ItemTouchHelper touchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull  RecyclerView recyclerView, @NonNull  RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull  RecyclerView recyclerView, @NonNull  RecyclerView.ViewHolder viewHolder, @NonNull  RecyclerView.ViewHolder target) {
                userAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull  RecyclerView.ViewHolder viewHolder, int direction) {
                userAdapter.onItemDismiss(viewHolder.getAdapterPosition());
            }
        });
        touchHelper.attachToRecyclerView(binding.rv);

    }

    private void loadUsers() {
    userList=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userList.add(new User("Janob Rasul","Captiva"+i));
        }

    }
}