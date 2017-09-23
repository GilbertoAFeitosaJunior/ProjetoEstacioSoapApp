package br.com.projetoestacioapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import br.com.projetoestacioapp.util.Constants;


public class SplashActivity extends Activity implements Runnable {

    private Handler splashScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashScreen = new Handler();
        splashScreen.postDelayed(SplashActivity.this, 450);
    }
    @Override
    public void run() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
        String login = sharedPreferences.getString(Constants.USUARIO_LOGADO, "");
        if (login.equals("LOGADO")) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(new Intent(SplashActivity.this, LoginActivity.class)));
            finish();
        }
    }

}