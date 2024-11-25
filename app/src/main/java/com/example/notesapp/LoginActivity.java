package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity
{
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //fields in login screen
        TextView username = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        MaterialButton loginbtn = findViewById(R.id.loginbtn);
        MaterialButton gotoregisterbtn = findViewById(R.id.gotoregisterbtn);
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon, 0, R.drawable.eye_on, 0);


        //when click on login button
        loginbtn.setOnClickListener(view ->
        {
            String usernameText= username.getText().toString().trim();
            String passwordText= passwordEditText.getText().toString().trim();

            //setting default username and password for logging in
            if(usernameText.equals("admin") && passwordText.equals("admin"))
            {
                //goto home screen
                Fragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.login,homeFragment).commit();
            }
            else    //if password / username are different; it shows a warning message
                Toast.makeText(LoginActivity.this, "Incorrect username or password\nPlease check them again", Toast.LENGTH_SHORT).show();

        });

        //a button sends back to register screen
        gotoregisterbtn.setOnClickListener(view ->
        {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        //hide / show password
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
   });
}
}