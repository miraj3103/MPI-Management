package com.model.institute;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
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

public class teacher_signup extends AppCompatActivity {

    ImageView teacher_profile_photo;
    TextInputEditText teacher_ed_name,teacher_ed_contact_number,teacher_ed_department,teacher_ed_position,teacher_ed_gmail,teacher_ed_password,teacher_ed_blood;
    AppCompatButton teacher_btn_sign_up;
    TextView teacher_login,teacher_profile_photo_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_signup);


    //*************---Find View By id Start----***********\\
        teacher_profile_photo = findViewById(R.id.teacher_profile_photo);
        teacher_ed_name = findViewById(R.id.teacher_ed_name);
        teacher_ed_contact_number = findViewById(R.id.teacher_ed_contact_number);
        teacher_ed_department = findViewById(R.id.teacher_ed_department);
        teacher_ed_position = findViewById(R.id.teacher_ed_position);
        teacher_ed_gmail = findViewById(R.id.teacher_ed_gmail);
        teacher_ed_password = findViewById(R.id.teacher_ed_password);
        teacher_ed_blood = findViewById(R.id.teacher_ed_blood);
        teacher_btn_sign_up = findViewById(R.id.teacher_btn_sign_up);
        teacher_login = findViewById(R.id.teacher_login);
        teacher_profile_photo_change = findViewById(R.id.teacher_profile_photo_change);
        //*************---Find View By id end----***********\\

        //*************---Teacher Button Sign Up  Start----***********\\
        teacher_btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = teacher_ed_name.getText().toString();
                String contact_number = teacher_ed_contact_number.getText().toString();
                String department = teacher_ed_department.getText().toString();
                String position = teacher_ed_position.getText().toString();
                String gmail = teacher_ed_gmail.getText().toString();
                String password = teacher_ed_password.getText().toString();
                String blood = teacher_ed_blood.getText().toString();


                //*************---Sign up Image drawable Start----***********\\
                BitmapDrawable bitmapDrawable = (BitmapDrawable) teacher_profile_photo.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                byte[] imageByte = outputStream.toByteArray();
                String image = Base64.encodeToString(imageByte,Base64.DEFAULT);
                //*************---Sign up Image drawable end----***********\\


                //simple volley string request
                String url = "https://www.google.com/";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map myMap = new HashMap<String, String>();
                        myMap.put("name",name);
                        myMap.put("number", contact_number);
                        myMap.put("department", department);
                        myMap.put("position", position);
                        myMap.put("gmail", gmail);
                        myMap.put("password", password);
                        myMap.put("blood", blood);
                        myMap.put("image", image);
                        return myMap;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(teacher_signup.this);
                queue.add(stringRequest);

                //simple volley string request end
            }
        });
        //*************---Teacher Button Sign Up  end----***********\\


    }
}