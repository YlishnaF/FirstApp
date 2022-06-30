package com.example.firstapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.model.Calculator;
import com.example.firstapp.model.Operator;
import com.example.firstapp.presenter.CalculatorPresenter;
import com.example.firstapp.ui.CalculatorView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView {
    private TextView resultTxt;
    private CalculatorPresenter presenter;
    private RadioButton checkBox_dark;
    private RadioButton checkBox_light;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("themes.xml", Context.MODE_PRIVATE);

        int theme = preferences.getInt("theme", R.style.AppTheme);

        setTheme(theme);

        setContentView(R.layout.activity_main);
        presenter = new CalculatorPresenter(this, new Calculator());

        resultTxt = findViewById(R.id.result);

        checkBox_dark = findViewById(R.id.night_theme);
        checkBox_light =findViewById(R.id.light_theme);

        if (checkBox_dark != null) {
            checkBox_dark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    preferences.edit()
                            .putInt("theme", R.style.NightAppTheme)
                            .commit();
                    checkBox_light=null;

                    recreate();
                }
            });
        }

        if (checkBox_light != null) {
            checkBox_light.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences.edit()
                            .putInt("theme", R.style.AppTheme)
                            .commit();
                    recreate();
                    checkBox_dark=null;

                }
            });
        }
        presenter = new CalculatorPresenter(this, new Calculator());

        final Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(R.id.key_0, 0);
        numbers.put(R.id.key_1, 1);
        numbers.put(R.id.key_2, 2);
        numbers.put(R.id.key_3, 3);
        numbers.put(R.id.key_4, 4);
        numbers.put(R.id.key_5, 5);
        numbers.put(R.id.key_6, 6);
        numbers.put(R.id.key_7, 7);
        numbers.put(R.id.key_8, 8);
        numbers.put(R.id.key_9, 9);

        View.OnClickListener digitClicListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDigitPressed(numbers.get(v.getId()));
            }
        };
        findViewById(R.id.key_0).setOnClickListener(digitClicListener);
        findViewById(R.id.key_1).setOnClickListener(digitClicListener);
        findViewById(R.id.key_2).setOnClickListener(digitClicListener);
        findViewById(R.id.key_3).setOnClickListener(digitClicListener);
        findViewById(R.id.key_4).setOnClickListener(digitClicListener);
        findViewById(R.id.key_5).setOnClickListener(digitClicListener);
        findViewById(R.id.key_6).setOnClickListener(digitClicListener);
        findViewById(R.id.key_7).setOnClickListener(digitClicListener);
        findViewById(R.id.key_8).setOnClickListener(digitClicListener);
        findViewById(R.id.key_9).setOnClickListener(digitClicListener);

        final Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_minus, Operator.MINUS);
        operators.put(R.id.key_plus, Operator.PLUS);
        operators.put(R.id.key_multiply, Operator.MULTIPLY);
        operators.put(R.id.key_div, Operator.DIV);

        View.OnClickListener operatorsClicListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperatorPressed(operators.get(v.getId()));
            }
        };
        findViewById(R.id.key_minus).setOnClickListener(operatorsClicListener);
        findViewById(R.id.key_multiply).setOnClickListener(operatorsClicListener);
        findViewById(R.id.key_div).setOnClickListener(operatorsClicListener);
        findViewById(R.id.key_plus).setOnClickListener(operatorsClicListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed();
            }
        });


    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);

    }

}
