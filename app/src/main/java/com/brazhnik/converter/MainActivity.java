package com.brazhnik.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText et_money;
    private Button btn_converte;
    private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_money = (EditText) findViewById(R.id.et_money);
        btn_converte = (Button) findViewById(R.id.btn_converte);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_converte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double rubles, dollars;
                String rublesText = et_money.getText().toString();
                rubles = Double.parseDouble(rublesText);
                dollars = rubles/60;
                String result = Double.toString(dollars);
                tv_result.setText(result);
            }
        });
    }
}
