package com.example.firstapp.model;

public class Calculator implements Calculate {
    @Override
    public double calculate(double a, double b, Operator operator) {
        switch (operator){
            case DIV:
                return a/b;
            case PLUS:
                return a+b;
            case MINUS:
                return a-b;
            case MULTIPLY:
                return a*b;
        }
        return 0.0;
    }
}
