package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class TmbActivity extends AppCompatActivity {

    private EditText editWeight, editHeight, editAge;
    private Spinner spinnerTmb;
    private Button btnCalcTmb;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmb);

        editWeight = findViewById(R.id.edit_tmb_weight);
        editHeight = findViewById(R.id.edit_tmb_height);
        editAge = findViewById(R.id.edit_tmb_age);
        spinnerTmb = findViewById(R.id.spinner_tmb);
        btnCalcTmb = findViewById(R.id.btn_calc_tmb);
        ImageView searchTmb = findViewById(R.id.search_tmb);

        radioGroup = findViewById(R.id.radioGroup);


        popUpMenu(searchTmb);


        btnCalcTmb.setOnClickListener(view -> {

            if (!validate()) {
                Toast.makeText(getApplicationContext(), "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
                return;
            }


        });

    }


    private boolean validate() {
        return (!editWeight.getText().toString().isEmpty() &&
                !editHeight.getText().toString().isEmpty() &&
                !editAge.getText().toString().isEmpty() &&
                !editWeight.getText().toString().startsWith("0") &&
                !editHeight.getText().toString().startsWith("0") &&
                !editAge.getText().toString().startsWith("0") &&
                radioGroup.getCheckedRadioButtonId() != -1);
    }


    private void popUpMenu(ImageView search) {
        search.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(TmbActivity.this, search);
            popupMenu.inflate(R.menu.menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.menu_list:
                        openListResult();
                        return true;
                    default:
                        return false;
                }
            });
            popupMenu.show();
        });
    }

    private void openListResult() {
        Intent intent = new Intent(TmbActivity.this, ListResultActivity.class);
        intent.putExtra("result", "tmb");
        startActivity(intent);
    }

}