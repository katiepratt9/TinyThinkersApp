package com.example.tinythinkers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

public class ForgotPasswordPage extends AppCompatActivity {
    EditText emailField;
    Button sendBtn, bckBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forgot_password);
        emailField = findViewById(R.id.email);
        sendBtn = findViewById(R.id.recover);
        bckBtn = findViewById(R.id.back2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString().trim();
                if (email.isEmpty())
                {
                    Toast.makeText(ForgotPasswordPage.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822"); //tells Android we want an email app - didn't work with old code bc gmail Gmail does not accept EXTRA_SUBJECT or EXTRA_TEXT when using Intent.ACTION_SENDTO
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Password Recovery");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Hello,\n\n" +
                                "You requested a password reset.\n" +
                                "Please click the link to recover your account:\n\n" +
                                "https://fake-reset-link.com/reset?user=" + email + "\n\n" +
                                "Thank you!");
                startActivity(Intent.createChooser(intent, "Choose Email App"));
            }
        });
        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ForgotPasswordPage.this, LoginPage.class);
                startActivity(back);
            }
        });
    }
}