package com.intecap.carrosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class splashScreen extends AppCompatActivity {

    LottieAnimationView mainObject, Loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mainObject = findViewById(R.id.LottieSplashMain);
        Loading = findViewById(R.id.LottieSplashLoading);
        animatingLottie(mainObject, R.raw.cariso);
        animatingLottie(Loading, R.raw.loadone);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1500);
    }
    private void animatingLottie(LottieAnimationView view, int raw)
    {
        view.setAnimation(raw);
        view.loop(true);
        view.playAnimation();
    }
}