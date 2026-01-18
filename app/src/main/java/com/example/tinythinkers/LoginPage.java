package com.example.tinythinkers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {

    EditText usernameEdit, passwordEdit;
    Button confirmButton;
    TextView forgotpass, createaccount;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameEdit = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
        confirmButton = findViewById(R.id.confirm);
        forgotpass = findViewById(R.id.forgotpassword);
        createaccount = findViewById(R.id.createaccount);
        prefs = getSharedPreferences("LoginPrefs",MODE_PRIVATE);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginPage.this, "Enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject users = getUserData();

                if (!users.has(username)) {
                    Toast.makeText(LoginPage.this, "Account does not exist or wrong username", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    String savedPass = users.getString(username);

                    if (savedPass.equals(password)) {
                        Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(LoginPage.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginPage.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(LoginPage.this, "Error reading account", Toast.LENGTH_SHORT).show();
                }
            }

        });
        forgotpass.setOnClickListener(v -> {
            Intent fp = new Intent(LoginPage.this, ForgotPasswordPage.class);
            LoginPage.this.startActivity(fp);
        });
        createaccount.setOnClickListener(v -> {
            Intent ca = new Intent(LoginPage.this, CreateAccountPage.class);
            LoginPage.this.startActivity(ca);
        });
    }
    private JSONObject getUserData() {
        String json = prefs.getString("USER_DATA", "{}");
        try
        {
            return new JSONObject(json);
        }
        catch (Exception e)
        {
            return new JSONObject();
        }
    }
}