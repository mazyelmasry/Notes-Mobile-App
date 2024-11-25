package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        //animated objects in splash screen
        ImageView notesicon = findViewById(R.id.notesIcon);
        TextView quote = findViewById(R.id.quote);
        TextView appName = findViewById(R.id.appname);

        //splash screen logo animation
        Animation noteAnimation = AnimationUtils.loadAnimation(this, R.anim.notes_icon_animation);
        notesicon.startAnimation(noteAnimation);

        //animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        fadeIn.setStartOffset(500);
        quote.startAnimation(fadeIn);
        appName.startAnimation(fadeIn);

        //Directing from splash screen to login screen
        new Handler().postDelayed(() ->
        {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
   });
}
}