package com.example.notesapp;

import android.os.Bundle;
import android.text.TextUtils;
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

    private boolean isPasswordVisible = false; // Tracks the visibility state of the password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        TextView username = findViewById(R.id.username);
        TextView email = findViewById(R.id.email);
        MaterialButton signupbtn = findViewById(R.id.signupbtn);
        EditText passwordEditText = findViewById(R.id.password);

        signupbtn.setOnClickListener(view ->
        {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";

            String usernameText= username.getText().toString().trim();
            if (TextUtils.isEmpty(usernameText))
            {
                username.setError("Username is required !");
                return;
            }


//            else
//            {
//                Toast.makeText(RegisterActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//            }

            String emailText= email.getText().toString().trim();
            if (TextUtils.isEmpty(emailText))
            {
                email.setError("Email is required !");
                return;
            } else if (!Pattern.matches(emailRegex,emailText))
            {
                email.setError("Invalid Email format\nex: youremail@mail.domain");
            }

//            else
//            {
//                Toast.makeText(RegisterActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//            }

            String passwordText= passwordEditText.getText().toString().trim();
            if (TextUtils.isEmpty(passwordText))
            {
                passwordEditText.setError("Password is required !");
                return;
            } else if (Pattern.matches(passwordRegex,passwordText) == false)
            {
                passwordEditText.setError("Password must contain at least 8 characters including uppercase, lowercase, number, special character");
            }

//            else
//            {
//                Toast.makeText(RegisterActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//            }
        });


        // Handle Eye Icon Click
//        passwordEditText.setOnTouchListener((v, event) -> {
//            Log.d("DEBUG", "OnTouchListener triggered");
//            if (event.getAction() == MotionEvent.ACTION_UP) {
//                // Check if the touch is within the bounds of drawableRight
//                if (event.getRawX() >= (passwordEditText.getRight() -
//                        passwordEditText.getCompoundDrawables()[2].getBounds().width())) {
//                    // Toggle password visibility
//                    isPasswordVisible = !isPasswordVisible;
//
//                    if (isPasswordVisible) {
//                        // Show password and change icon to "eye_off"
//                        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
//                                R.drawable.password_icon, 0, R.drawable.eye_off, 0); // Update icon
//                    } else {
//                        // Hide password and change icon to "eye_on"
//                        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
//                                R.drawable.password_icon, 0, R.drawable.eye_on, 0); // Update icon
//                    }
//
//                    // Move the cursor to the end of the text
//                    passwordEditText.setSelection(passwordEditText.getText().length());
//                    return true;
//                }
//            }
//            return false;
//        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
   });
}
}