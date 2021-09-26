package com.example.fitnesstracker;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {


    private EditText editWeight, editHeight;

    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);


        editWeight = findViewById(R.id.edit_weight);
        editHeight = findViewById(R.id.edit_height);
        Button btnCalc = findViewById(R.id.btn_calc);


        btnCalc.setOnClickListener(view -> {

            if (!validate()) {
                Toast.makeText(getApplicationContext(), "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
                return;
            }

            int peso = Integer.parseInt(editWeight.getText().toString());
            int altura = Integer.parseInt(editHeight.getText().toString());


            double calc = calculate(peso, altura);

            int response = imcResponse(calc);


            dialog = new Dialog(ImcActivity.this);
            dialog.setContentView(R.layout.custom_dialog);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_custom_dialog));
            }

            TextView imcCalcRes = dialog.findViewById(R.id.imc_calc_res);
            TextView imcResponseRes = dialog.findViewById(R.id.imc_response_res);
            Button save_imc = dialog.findViewById(R.id.save_imc);
            Button ok_imc = dialog.findViewById(R.id.ok_imc);

            imcCalcRes.setText(getString(R.string.imc_response,calc));
            imcResponseRes.setText(response);


            save_imc.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            ok_imc.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            dialog.show();


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);

        });
    }

    private double calculate(int peso, int altura) {
        return peso / (((double) altura / 100) * ((double) altura / 100));
    }

    @StringRes
    private int imcResponse(double imc) {
        if (imc < 18.5) {
            return R.string.magreza;
        } else if (imc >= 18.5 && imc <= 24.9) {
            return R.string.pesoNormal;
        } else if (imc >= 25 && imc <= 29.9) {
            return R.string.sobrepeso;
        } else if (imc >= 30 && imc <= 39.9) {
            return R.string.obesidade;
        } else {
            return R.string.obesidadeGrave;
        }
    }

    private boolean validate() {
        return (!editWeight.getText().toString().isEmpty() &&
                !editHeight.getText().toString().isEmpty() &&
                !editWeight.getText().toString().startsWith("0") &&
                !editHeight.getText().toString().startsWith("0"));
    }
}