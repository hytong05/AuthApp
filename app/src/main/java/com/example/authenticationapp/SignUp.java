package com.example.authenticationapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    TextView loginText;
    EditText username;
    EditText email;
    EditText password;
    Button signupButton;
    SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        db = new SQLiteConnector(this);

        loginText = findViewById(R.id.loginRedirectText);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(v -> {
            String user = username.getText().toString();
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass)) {
                if (!db.checkUser(mail)) {
                    Info newUser = new Info();
                    newUser.setName(user);
                    newUser.setEmail(mail);
                    newUser.setPassword(pass);
                    db.addUser(newUser);

                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Email is already registered!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(SignUp.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
            }
        });
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
        });
    }
}