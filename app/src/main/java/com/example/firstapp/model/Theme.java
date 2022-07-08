package com.example.firstapp.model;

import androidx.annotation.StyleRes;

import com.example.firstapp.R;

public enum Theme {
    LIGHT(R.style.AppTheme, R.string.light_theme, "light"),
    DARK(R.style.NightAppTheme, R.string.dark_theme, "dark");

    @StyleRes
    private int themeRes;
    @StyleRes
    private int title;
    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
