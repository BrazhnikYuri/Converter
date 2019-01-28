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
                if (rubles % 60 != 0) {
                    dollars = rubles / 60;
                    tvResult.setText(getString(R.string.t_result) + String.format("%.2f", +dollars).replace(",", "."));
                } else {
                    dollars = rubles / 60;
                    tvResult.setText(getString(R.string.t_result) + String.format("%.0f", +dollars));
                }
            }
        });
    }
}
