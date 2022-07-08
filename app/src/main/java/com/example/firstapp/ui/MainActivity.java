package com.example.firstapp.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.model.Calculator;
import com.example.firstapp.model.Operator;
import com.example.firstapp.model.Theme;
import com.example.firstapp.model.ThemeRepository;
import com.example.firstapp.model.ThemeRepositoryImpl;
import com.example.firstapp.presenter.CalculatorPresenter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView {
    private TextView resultTxt;
    private CalculatorPresenter presenter;
    private ThemeRepository themeRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository= ThemeRepositoryImpl.getINSTANCE(this);

        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_main);
        presenter = new CalculatorPresenter(this, new Calculator());

        resultTxt = findViewById(R.id.result);
        final ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK){
                    Intent intent = result.getData();

                    Theme selectedTheme = (Theme) intent.getSerializableExtra(SelectThemeActivity.THEME_SELECTED);
                    themeRepository.saveTheme(selectedTheme);
                    recreate();
                }

            }
        });

       findViewById(R.id.theme).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, SelectThemeActivity.class);
               intent.putExtra(SelectThemeActivity.THEME_SELECTED, themeRepository.getSavedTheme());
               themeLauncher.launch(intent);
           }
       });

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
