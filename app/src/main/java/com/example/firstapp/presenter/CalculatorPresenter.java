package com.example.firstapp.presenter;

import com.example.firstapp.model.Calculate;
import com.example.firstapp.model.Operator;
import com.example.firstapp.ui.CalculatorView;

public class CalculatorPresenter {
    private CalculatorView view;
    private Calculate calculator;
    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;
    private boolean isDotPressed = false;
    int por = 10;

    public CalculatorPresenter() {
    }

    public CalculatorPresenter(CalculatorView view, Calculate calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int integer) {

        if (argTwo == null) {
            view.showResult("");
            if (isDotPressed) {
                if (selectedOperator == null) {
                    argOne = argOne + (double) integer / por;

                    view.showResult(String.format("%.2f", argOne));
                    por *= 10;
                }
            } else {
                argOne = argOne * 10 + integer;
                view.showResult(String.format("%.2f", argOne));
                por = 10;
            }

        } else {
            if (isDotPressed) {
                argTwo = argTwo + (double) integer / por;
                view.showResult(String.format("%.2f", argOne) + operatorToString(selectedOperator) + String.format("%.2f", argTwo));
                por *= 10;


            } else {
                argTwo = argTwo * 10 + integer;
                view.showResult(String.format("%.2f", argOne) + operatorToString(selectedOperator) + String.format("%.2f", argTwo));
                por = 10;
            }

        }

    }

    public void onOperatorPressed(Operator operator) {
        isDotPressed = false;
        if (selectedOperator != null) {
            argOne = calculator.calculate(argOne, argTwo, selectedOperator);
            view.showResult(String.valueOf(argOne));
        }
        selectedOperator = operator;
        view.showResult(String.format("%.2f", argOne) + operatorToString(selectedOperator));
        argTwo = 0.0;

    }

    public void onDotPressed() {
        isDotPressed = true;
    }

    protected String operatorToString(Operator operator) {
        switch (operator) {
            case MULTIPLY:
                return "*";
            case MINUS:
                return "-";
            case PLUS:
                return "+";
            case DIV:
                return "/";
        }
        return "";
    }

}
