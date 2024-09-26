package com.model.institute;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

public class login_Page extends AppCompatActivity {

    TextInputEditText ed_gmail, ed_password;
    AppCompatButton btn_login;
    TextView forgot_pass, sign_upp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ed_gmail = findViewById(R.id.ed_gmail);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        forgot_pass = findViewById(R.id.forgot_pass);
        sign_upp = findViewById(R.id.sign_upp);





        //  --*********** under line text make korar jonno---\\

        forgot_pass.setText(Html.fromHtml("<u>Forgot Password?</u>"));
        sign_upp.setText(Html.fromHtml("<u>Sign Up</u>"));

        //  --*********** under line text make korar ses---\\

        sign_upp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        new AlertDialog.Builder(login_Page.this)
                                .setTitle("Are you Student Or Teacher?")
                                .setIcon(R.drawable.question_mark)
                                .setPositiveButton("Student", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        startActivity(new Intent(login_Page.this,student_signup.class));



                                    }
                                })
                                .setNegativeButton("Teacher", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        startActivity(new Intent(login_Page.this,teacher_signup.class));

                                    }
                                })
                                .setCancelable(false)
                                .show();

                    }
                }, 10);


            }
        });


        //  --*********** signup set on click end--**********---\\

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_Page.this,MainActivity.class));
            }
        });

    }
}


//final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//    @Override
//    public void run() {
//        //Code here
//        startActivity( new Intent(login_Page.this,SIgn_up.class));
//        finish();
//    }
//}, 500);

