package com.model.institute;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class student_signup extends AppCompatActivity {

    TextInputEditText ed_name, ed_roll, ed_registration, ed_season, ed_department, ed_contact_number, ed_gmail, ed_password, ed_blood;
    AppCompatButton btn_sign_up;
    TextView login,student_profile_photo_change;
    ImageView student_profile_photo;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup);


        //  --*********** Find View By Id start----*********---\\
        student_profile_photo = findViewById(R.id.student_profile_photo);
        student_profile_photo_change = findViewById(R.id.student_profile_photo_change);
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

        //  --*********** Find View By Id end-----*********---\\


        //  --*********** under line text make korar jonno---\\
        login.setText(Html.fromHtml("<u>Login?</u>"));
        //  --*********** under line text make korar ses---\\



        // --***********-- Button signup Start --************\\
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Objects.requireNonNull(ed_name.getText()).toString();
                String roll = Objects.requireNonNull(ed_roll.getText()).toString();
                String registration = Objects.requireNonNull(ed_registration.getText()).toString();
                String season = Objects.requireNonNull(ed_season.getText()).toString();
                String department = Objects.requireNonNull(ed_department.getText()).toString();
                String contact_number = Objects.requireNonNull(ed_contact_number.getText()).toString();
                String gmail = Objects.requireNonNull(ed_gmail.getText()).toString();
                String password = Objects.requireNonNull(ed_password.getText()).toString();
                String blood = Objects.requireNonNull(ed_blood.getText()).toString();


                //*************--- sign Up  image bitmap Start----***********\\

                BitmapDrawable bitmapDrawable = (BitmapDrawable) student_profile_photo.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                byte [] imagebyte = outputStream.toByteArray();
                String image = Base64.encodeToString(imagebyte,Base64.DEFAULT);

                //*************---sign Up image bitmap end----***********\\


                //simple volley string request
                String url = "http://192.168.0.103/mpi/connect_database.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new AlertDialog.Builder(student_signup.this)
                                .setTitle("Sign up response")
                                .setMessage(response)
                                .create()
                                .show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(student_signup.this,""+error,Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(student_signup.this)
                                .setTitle("Sign up response")
                                .setMessage(""+error)
                                .create()
                                .show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map myMap = new HashMap<String, String>();
                        myMap.put("name",name);
                        myMap.put("roll", roll);
                        myMap.put("registration", registration);
                        myMap.put("season", season);
                        myMap.put("department", department);
                        myMap.put("number", contact_number);
                        myMap.put("gmail", gmail);
                        myMap.put("password", password);
                        myMap.put("blood", blood);
                        myMap.put("image", image);
                        myMap.put("key",EncryptionDecryption.MY_KEY);
                        return myMap;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(student_signup.this);
                queue.add(stringRequest);

                //simple volley string request end

            }
        });
        // --***********-- Button signup Start --************\\



        //*************---Login sign in Start----***********\\
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login_Page.class));
            }
        });
        //*************---Login sign in Start----***********\\



    } /*-- One create end */


}

