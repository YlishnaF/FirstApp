package com.example.firstapp.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository {
    private static ThemeRepositoryImpl INSTANCE;

    private SharedPreferences preferences;
    private static final String KEY_THEME="KEY_THEME";

    public static ThemeRepositoryImpl getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE = new ThemeRepositoryImpl(context);
        }
            return INSTANCE;
    }

    public ThemeRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences("theme.xml", Context.MODE_PRIVATE);
    }

    @Override
    public Theme getSavedTheme() {
        String savedKey = preferences.getString(KEY_THEME, Theme.LIGHT.getKey());

        for (Theme item: Theme.values()) {
            if (item.getKey().equals(savedKey)) {
                return item;
            }
        }
        return Theme.LIGHT;
    }

    @Override
    public void saveTheme(Theme theme) {
        preferences.edit()
                .putString(KEY_THEME, theme.getKey())
                .apply();
    }

    @Override
    public List<Theme> getAllTheme() {
        return Arrays.asList(Theme.values());

    }
}
