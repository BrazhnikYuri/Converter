package com.brazhnik.converter;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText etMoney;
    private Button btnConvert;
    private TextView tvResult;
    private TextInputLayout tilError;
    double[][] currencyCourse = new double[3][3];

    public void fillCourses() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillCourses();
        tilError = (TextInputLayout) findViewById(R.id.til_et_money);
        etMoney = (EditText) findViewById(R.id.et_money);
        btnConvert = (Button) findViewById(R.id.btn_convert);
        tvResult = (TextView) findViewById(R.id.tv_result);
        tvResult.setText(R.string.text_result);
        tilError.setHint(getString(R.string.t_support));

        TextWatcher etMoneyWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().equalsIgnoreCase("")) {
                    String checkZeroError = etMoney.getText().toString();
                    double checkZeroErrorDouble = Double.parseDouble(checkZeroError);
                    if (checkZeroErrorDouble == 0) {
                        tilError.setError(getString(R.string.t_zero_error));
                    } else {
                        tilError.setError(null);
                    }
                } else {
                    tilError.setError(getString(R.string.t_null_error));
                }
            }
        };
        etMoney.addTextChangedListener(etMoneyWatcher);

        final Spinner firstCurrency = (Spinner) findViewById(R.id.first_currency);
        final Spinner secondCurrency = (Spinner) findViewById(R.id.second_currency);
        ArrayAdapter <?> currencyArrayAdapter = ArrayAdapter.createFromResource(this, R.array.sp_currency, R.layout.support_simple_spinner_dropdown_item);
        firstCurrency.setAdapter(currencyArrayAdapter);
        secondCurrency.setAdapter(currencyArrayAdapter);
        firstCurrency.setSelection(0);
        secondCurrency.setSelection(1);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tilError.setError(null);
                int firstCurrencyPosition = firstCurrency.getSelectedItemPosition();
                int secondCurrencyPosition = secondCurrency.getSelectedItemPosition();
                double amountMoney, resultMoney;
                String amountMoneyText = etMoney.getText().toString();
                amountMoney = Double.parseDouble(amountMoneyText);
                if(amountMoney != 0) {
                    resultMoney = amountMoney * currencyCourse[firstCurrencyPosition][secondCurrencyPosition];
                    DecimalFormat newResultMoney = new DecimalFormat("###.##");
                    tvResult.setText(getString(R.string.t_result, newResultMoney.format(resultMoney)));
                } else{
                    tvResult.setText(getString(R.string.t_result));
                }
            }
        });
    }
}