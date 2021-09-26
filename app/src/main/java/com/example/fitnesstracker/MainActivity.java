package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitnesstracker.model.MainItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rv_main);


        rvMain.setLayoutManager(new GridLayoutManager(this,2));


        List<MainItem> mainItems = new ArrayList<>();
        mainItems.add(new MainItem(1,R.drawable.fita_metrica,R.string.imc_title));
        mainItems.add(new MainItem(2,R.drawable.tmb_icon,R.string.tmb_title));
        mainItems.add(new MainItem(3,R.drawable.balance,R.string.ideal_weight));
        //mainItems.add(new MainItem(4,R.drawable.records,R.string.records));

        MainAdapter mainAdapter = new MainAdapter(getApplicationContext(),mainItems);

        mainAdapter.setListener(id -> {
            switch (id){
                case 1:
                    startActivity(new Intent(MainActivity.this,ImcActivity.class));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        });


        rvMain.setAdapter(mainAdapter);


    }
}