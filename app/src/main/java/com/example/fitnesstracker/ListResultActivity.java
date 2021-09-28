package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fitnesstracker.data.SqlHelper;
import com.example.fitnesstracker.model.Register;

import java.util.ArrayList;
import java.util.List;

public class ListResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        RecyclerView rvResult = findViewById(R.id.rv_result_list);
        

        String type = getIntent().getExtras().getString("result");

        List<Register> registerList = SqlHelper.getInstance(ListResultActivity.this).getListRegisterBy(type);

        rvResult.setLayoutManager(new LinearLayoutManager(this));

        ListResultAdapter listResultAdapter = new ListResultAdapter(ListResultActivity.this, registerList);

        rvResult.setAdapter(listResultAdapter);


    }
}