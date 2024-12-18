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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity
{
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //fields in register screen
        TextView username = findViewById(R.id.username);
        TextView email = findViewById(R.id.email);
        MaterialButton signupbtn = findViewById(R.id.signupbtn);
        MaterialButton gotologinbtn = findViewById(R.id.gotologinbtn);
        EditText passwordEditText = findViewById(R.id.password);
        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon, 0, R.drawable.eye_on, 0);

        //when click on sign up button
        signupbtn.setOnClickListener(view ->
        {
            //email and password regex
            String usernameRegex = "^(?!\\d+$)(?!\\d+[a-zA-Z])[a-zA-Z]+(\\d*[a-zA-Z]*)*$";
            String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";

            //checking if username field is empty
            boolean usernameFormat = false;
            String usernameText= username.getText().toString().trim();
            if (TextUtils.isEmpty(usernameText))
            {
                username.setError("Username is required !");
                return;
            } else if (!Pattern.matches(usernameRegex,usernameText))
            {
                username.setError("Invalid username format");
            }
            else usernameFormat = true;

            //checking if email field is empty
            boolean emailFormat = false;
            String emailText= email.getText().toString().trim();
            if (TextUtils.isEmpty(emailText))
            {
                email.setError("Email is required !");
                return;
            }
            //checking if if email does not match email regex
            else if (!Pattern.matches(emailRegex,emailText))
            {
                email.setError("Invalid Email format\nex: youremail@mail.domain");
            }
            else emailFormat = true;

            //checking if password field is empty
            boolean passwordFormat = false;
            String passwordText= passwordEditText.getText().toString().trim();
            if (TextUtils.isEmpty(passwordText))
            {
                passwordEditText.setError("Password is required !");
                return;
            }
            //checking if if password does not match email regex
            else if (!Pattern.matches(passwordRegex,passwordText))
            {
                passwordEditText.setError("Password must contain at least 4 characters including at least one uppercase. one lowercase and one number");
            }
            else passwordFormat = true;

            //if all fields are fine -> it directs to home screen
            if(usernameFormat && emailFormat && passwordFormat)
            {
                Fragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.register,homeFragment).commit();
            }
        });

        gotologinbtn.setOnClickListener(view ->
        {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

        });


        //hide / show password logic
        passwordEditText.setOnTouchListener((v, event) ->
        {
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
   });
}
}