package com.model.institute;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageSlider imageSlider;
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    TextView time,marquee_text;
    GridView gridView;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //*************---Firebase cloud method start----***********\\
        askNotificationPermission();
        initToken();
        //*************---Firebase cloud method end----***********\\

        drawerLayout = findViewById(R.id.drawerLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        time = findViewById(R.id.time);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        marquee_text = findViewById(R.id.marquee_text);


        //*************---marquee Start----***********\\
        marquee_text.setSelected(true);
        //*************---marquee end----***********\\


//*************---Calender Start----***********\\
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d \nyyyy", Locale.getDefault());
        String date = dateFormat.format(currentDate);
        time.setText(date);
        //*************---Calender end----***********\\


        //*************---NavigationView joint start----***********\\
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,materialToolbar,R.string.close_drawer,R.string.open_drawer);
        drawerLayout.addDrawerListener(toggle);
        //*************---NavigationView joint end----***********\\


        //*************---NavigationView start----***********\\

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.ratting){
                    Toast.makeText(getApplicationContext(),"Working",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if (item.getItemId() == R.id.feedback){
                    Toast.makeText(getApplicationContext(),"Working",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else if (item.getItemId() == R.id.privacy){
                    Toast.makeText(getApplicationContext(),"Working",Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        //*************---NavigationView  end----***********\\



        //*************---Image Slider Start----***********\\
        imageSlider =findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel("https://mpibd.com/admin-mpibd/upload_image/slider_image/1686210122-6280.JPG", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://mpibd.com/admin-mpibd/upload_image/slider_image/1686210171-7609.JPG",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://mpibd.com/admin-mpibd/upload_image/slider_image/1686210212-2547.JPG",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.miraj,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.img1,ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
        //*************---Image Slider end----***********\\


        //*************---Bottom NavigationView  start----***********\\
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home){
                    Toast.makeText(MainActivity.this,"Working",Toast.LENGTH_LONG).show();
                }else if (item.getItemId() == R.id.profile){
                    Toast.makeText(MainActivity.this,"Working",Toast.LENGTH_LONG).show();
                }else if (item.getItemId() == R.id.notification){
                    Toast.makeText(MainActivity.this,"Working",Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });
        //*************---Bottom NavigationView  end----***********\\


    }
    //*************---Firebase cloud message start----***********\\

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                      new AlertDialog.Builder(MainActivity.this)
                              .setTitle("Get Permission")
                              .setMessage("App ti sundor moto poricalona korte permission allow korun")
                              .setPositiveButton("okay", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
                                  }
                              })
                              .setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {

                                  }
                              })
                              .show();
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }


    //*************---Firebase cloud message end----***********\\


    //*************---Firebase push notification start----***********\\

    private void initToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("firebase", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        Log.d("firebaseToken", "token id : "+ token);

                    }
                });
    }

    //*************---Firebase push notification end----***********\\


}