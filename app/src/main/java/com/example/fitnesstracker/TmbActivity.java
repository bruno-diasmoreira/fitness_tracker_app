package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstracker.data.SqlHelper;

public class TmbActivity extends AppCompatActivity {

    private EditText editWeight, editHeight, editAge;
    private Spinner spinnerTmb;
    private Button btnCalcTmb;

    private Dialog dialog;

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


            int weight = Integer.parseInt(editWeight.getText().toString());
            int height = Integer.parseInt(editHeight.getText().toString());
            int age = Integer.parseInt(editAge.getText().toString());


            double result = calculateTmb(weight, height, age);

            double tmb = tmbResponse(result);


            dialog = new Dialog(TmbActivity.this);
            dialog.setContentView(R.layout.custom_dialog);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_custom_dialog));
            }

            TextView tmbCalcRes = dialog.findViewById(R.id.imc_calc_res);
            TextView tmbResponseRes = dialog.findViewById(R.id.imc_response_res);
            Button save_tmb = dialog.findViewById(R.id.save_imc);
            Button ok_tmb = dialog.findViewById(R.id.ok_imc);

            tmbCalcRes.setText(getString(R.string.tmb_response, tmb));
            tmbResponseRes.setText("");

            save_tmb.setOnClickListener(view1 -> {

                long calcId = SqlHelper.getInstance(TmbActivity.this).addItem("tmb", tmb);

                if (calcId > 0) {
                    Toast.makeText(getApplicationContext(), "Resultado salvo com sucesso !!", Toast.LENGTH_SHORT).show();
                    openListResult();
                }

                dialog.dismiss();
            });

            ok_tmb.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            dialog.show();


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);








        });

    }


    private double tmbResponse(double result) {

        int index = spinnerTmb.getSelectedItemPosition();
        switch (index) {
            case 0:
                return result * 1.2;
            case 1:
                return result * 1.375;
            case 2:
                return result * 1.55;
            case 3:
                return result * 1.725;
            case 4:
                return result * 1.9;
            default:
                return 0;
        }
    }

    private double calculateTmb(int weight, int height, int age) {

        int checkedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedId);

        if (radioButton.getText().toString().equalsIgnoreCase("Masculino")) {
            return 66 + (weight * 13.8) + (5 * height) - (6.8 * age);
        } else {
            return 665 + (weight * 9.6) + (1.8 * height) - (4.7 * age);
        }

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