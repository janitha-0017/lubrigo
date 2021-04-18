package com.example.lubrigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void oilTest (View view)  {
        Intent intent = new Intent(getApplicationContext(), OilTesterView.class);
        startActivity(intent);

    }

    public void oilRecommendationMenu (View view)  {
        Intent intent = new Intent(getApplicationContext(), OilRecommendationView.class);
        startActivity(intent);

    }
}
