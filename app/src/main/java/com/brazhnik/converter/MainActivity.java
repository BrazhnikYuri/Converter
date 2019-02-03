package com.brazhnik.converter;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etMoney;
    private Button btnConvert;
    private TextView tvResult;
    double[][] currencyCourse = new double[3][3];
    boolean fillArray = true;

    public double createCourses(boolean fillArray, int firstCurrencyPosition, int secondCurrencyPosition) {
        if (fillArray){
            for (int k = 0; k < currencyCourse.length; k++) {
                for (int h = 0; h < currencyCourse.length; h++) {
                    currencyCourse[k][h] = 0;
                }
                currencyCourse[k][k] = 1;
            }
            for (int i = 0; i < currencyCourse.length; i++) {
                for (int j = 0; j < currencyCourse.length; j++) {
                    if (currencyCourse[i][j] == 0) {
                        currencyCourse[i][j] = (int) (Math.random() * 100);
                        currencyCourse[j][i] = 1 / currencyCourse[i][j];
                    }
                }
            }
        }
        return currencyCourse[firstCurrencyPosition][secondCurrencyPosition];
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMoney = (EditText) findViewById(R.id.et_money);
        btnConvert = (Button) findViewById(R.id.btn_convert);
        tvResult = (TextView) findViewById(R.id.tv_result);
        final Spinner firstCurrency = (Spinner) findViewById(R.id.first_currency);
        final Spinner secondCurrency = (Spinner) findViewById(R.id.second_currency);
        tvResult.setText(R.string.text_result);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onClick(View view) {
                int firstCurrencyPosition = firstCurrency.getSelectedItemPosition();
                int secondCurrencyPosition = secondCurrency.getSelectedItemPosition();
                double amountMoney, resultMoney;
                String amountMoneyText = etMoney.getText().toString();
                amountMoney = Double.parseDouble(amountMoneyText);
                if (amountMoney != 0) {
                    resultMoney = amountMoney * createCourses(fillArray, firstCurrencyPosition, secondCurrencyPosition);
                    DecimalFormat newResultMoney = new DecimalFormat("###.##");
                    tvResult.setText(getString(R.string.t_result, newResultMoney.format(resultMoney)));
                    fillArray = false;
                } else {
                    Toast zeroError = Toast.makeText(getApplicationContext(), R.string.t_zero_error, Toast.LENGTH_SHORT);
                    zeroError.show();
                }
            }
        });
    }
}