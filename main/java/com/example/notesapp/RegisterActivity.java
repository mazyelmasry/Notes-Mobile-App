package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity
{
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        TextView username = findViewById(R.id.username);
        TextView email = findViewById(R.id.email);
        MaterialButton signupbtn = findViewById(R.id.signupbtn);
        EditText passwordEditText = findViewById(R.id.password);

        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon, 0, R.drawable.eye_on, 0);

        signupbtn.setOnClickListener(view ->
        {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";

            boolean usernameFormat = false;
            String usernameText= username.getText().toString().trim();
            if (TextUtils.isEmpty(usernameText))
            {
                username.setError("Username is required !");
                return;
            }
            else usernameFormat = true;


            boolean emailFormat = false;
            String emailText= email.getText().toString().trim();
            if (TextUtils.isEmpty(emailText))
            {
                email.setError("Email is required !");
                return;
            } else if (!Pattern.matches(emailRegex,emailText))
            {
                email.setError("Invalid Email format\nex: youremail@mail.domain");
            }
            else emailFormat = true;


            boolean passwordFormat = false;
            String passwordText= passwordEditText.getText().toString().trim();
            if (TextUtils.isEmpty(passwordText))
            {
                passwordEditText.setError("Password is required !");
                return;
            } else if (!Pattern.matches(passwordRegex,passwordText))
            {
                passwordEditText.setError("Password must contain at least 8 characters including uppercase, lowercase, number, special character");
            }
            else passwordFormat = true;


            if(usernameFormat && emailFormat && passwordFormat)
            {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }

        });


        passwordEditText.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordEditText.getRight() -
                        passwordEditText.getCompoundDrawables()[2].getBounds().width() -
                        passwordEditText.getPaddingRight())) {

                    isPasswordVisible = !isPasswordVisible;

                    if (isPasswordVisible) {
                        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.password_icon, 0, R.drawable.eye_off, 0);
                    } else {
                        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.password_icon, 0, R.drawable.eye_on, 0);
                    }

                    passwordEditText.setSelection(passwordEditText.getText().length());
                    return true;
                }
            }
            return false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
   });
}
}