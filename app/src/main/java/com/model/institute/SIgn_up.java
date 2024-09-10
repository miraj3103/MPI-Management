package com.model.institute;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class SIgn_up extends AppCompatActivity {

    TextInputEditText ed_name,ed_roll,ed_registration,ed_season,ed_department,ed_contact_number,ed_gmail,ed_password,ed_blood;
    AppCompatButton btn_sign_up;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        ed_name = findViewById(R.id.ed_name);
        ed_roll = findViewById(R.id.ed_roll);
        ed_registration = findViewById(R.id.ed_registration);
        ed_season = findViewById(R.id.ed_season);
        ed_department = findViewById(R.id.ed_department);
        ed_contact_number = findViewById(R.id.ed_contact_number);
        ed_gmail = findViewById(R.id.ed_gmail);
        ed_password = findViewById(R.id.ed_password);
        ed_blood = findViewById(R.id.ed_blood);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        login = findViewById(R.id.login);
        //  --*********** under line text make korar jonno---\\
        login.setText(Html.fromHtml("<u>Login?</u>"));
        //  --*********** under line text make korar ses---\\

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Code here
                        startActivity(new Intent(SIgn_up.this, MainActivity.class));
                        finish();
                    }
                }, 1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SIgn_up.this, login_Page.class));
                finish();
            }
        });



    }
}