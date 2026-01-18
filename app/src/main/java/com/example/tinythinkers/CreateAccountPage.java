package com.example.tinythinkers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

public class CreateAccountPage extends AppCompatActivity {
    EditText emailTxt, password1Txt, password2Txt;
    Button verifyBtn, bckBtn;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        emailTxt = findViewById(R.id.emailTxt);
        password1Txt = findViewById(R.id.password1Txt);
        password2Txt = findViewById(R.id.password2Txt);
        verifyBtn = findViewById(R.id.verify);
        bckBtn = findViewById(R.id.back);

        prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailTxt.getText().toString().trim();
                String p1 = password1Txt.getText().toString().trim();
                String p2 = password2Txt.getText().toString().trim();

                if (email.isEmpty() || p1.isEmpty() || p2.isEmpty()) {
                    Toast.makeText(CreateAccountPage.this, "All fields required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!p1.equals(p2)) {
                    Toast.makeText(CreateAccountPage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject users = getUserData();

                if (users.has(email)) {
                    Toast.makeText(CreateAccountPage.this, "Account already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    users.put(email, p1);
                    saveUserData(users);
                    Toast.makeText(CreateAccountPage.this, "Account created!", Toast.LENGTH_SHORT).show();

                    finish();
                }
                catch (Exception e) {
                    Toast.makeText(CreateAccountPage.this, "Error saving account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bckBtn.setOnClickListener(v -> {
            Intent back = new Intent(CreateAccountPage.this, LoginPage.class);
            startActivity(back);
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

    private void saveUserData(JSONObject obj) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("USER_DATA", obj.toString());
        editor.apply();
    }
}