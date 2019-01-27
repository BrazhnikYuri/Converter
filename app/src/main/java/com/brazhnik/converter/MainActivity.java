package com.brazhnik.converter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etMoney;
    private Button btnConvert;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMoney = (EditText) findViewById(R.id.et_money);
        btnConvert = (Button) findViewById(R.id.btn_convert);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double rubles, dollars;
                String rublesText = etMoney.getText().toString();
                rubles = Double.parseDouble(rublesText);
                dollars = rubles/60;
                String dollarsText = Double.toString(dollars);
                String result = getString(R.string.result, dollarsText);
                tvResult.setText(result);
            }
        });
    }
}
