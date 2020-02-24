package com.example.usulessfacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startApplication();
        iniUI();
        makeAnimation();
    }

    public void iniUI(){
        imageView = findViewById(R.id.image_SplashScreen);
        textView = findViewById(R.id.text_SplashScreen);
    }

    public void makeAnimation(){
        YoYo.with(Techniques.Wobble)
                .duration(900)
                .repeat(2)
                .playOn(imageView);
        YoYo.with(Techniques.RubberBand)
                .duration(900)
                .repeat(2)
                .playOn(textView);
    }

    private void startApplication(){
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
