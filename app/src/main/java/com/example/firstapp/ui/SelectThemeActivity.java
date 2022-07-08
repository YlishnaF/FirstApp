package com.example.firstapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapp.R;
import com.example.firstapp.model.Theme;
import com.example.firstapp.model.ThemeRepository;
import com.example.firstapp.model.ThemeRepositoryImpl;

import java.util.List;

public class SelectThemeActivity extends AppCompatActivity {
    public static final String THEME_SELECTED="THEME_SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);
        Intent intent = getIntent();
        Theme selectedTheme = (Theme) intent.getSerializableExtra(THEME_SELECTED);

        ThemeRepository themeRepository = ThemeRepositoryImpl.getINSTANCE(this);

        List<Theme> themes = themeRepository.getAllTheme();

        LinearLayout container = findViewById(R.id.container);

        for (final Theme theme : themes) {
            View itemView = getLayoutInflater().inflate(R.layout.iteme_theme, container, false);
            TextView title = itemView.findViewById(R.id.title);
            title.setText(theme.getTitle());
            CardView cardView = itemView.findViewById(R.id.theme_card);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent();
                    data.putExtra(THEME_SELECTED,theme);
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            });
            ImageView checked = itemView.findViewById(R.id.checked);
            if (theme.equals(selectedTheme)) {

                checked.setVisibility(View.VISIBLE);
            } else {
                checked.setVisibility(View.GONE);
            }
            container.addView(itemView);
        }
    }
}