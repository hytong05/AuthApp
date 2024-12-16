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

public class Login extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button loginButton;
    TextView signupText;
    SQLiteConnector db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        db = new SQLiteConnector(this);

        userName = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupText = findViewById(R.id.signupRedirectText);

        loginButton.setOnClickListener(v -> {
            String user = userName.getText().toString();
            String pass = password.getText().toString();

            if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
                if (db.checkUser(user, pass)) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("USERNAME", String.valueOf(user));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Incorrect username or password!", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(Login.this, "Please enter username and password!", Toast.LENGTH_SHORT).show();
            }
        });

        signupText.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        });
    }
}