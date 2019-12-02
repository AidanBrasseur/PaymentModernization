package com.example.paymentmodernization.MainActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.paymentmodernization.Login.LoginActivity;
import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

public class NavDrawer extends AppCompatActivity {

  private AppBarConfiguration mAppBarConfiguration;

  private TextView username;
  private TextView fullName;
  private Button signOut;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nav_drawer);
    Toolbar toolbar = findViewById(R.id.toolbar);
    signOut = findViewById(R.id.signOut);
    signOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(NavDrawer.this, LoginActivity.class);
        startActivity(intent);
      }
    });
    setSupportActionBar(toolbar);
    Intent intent = getIntent();
    UserInformation userInformation = intent.getParcelableExtra("userInformation");
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    View header = navigationView.getHeaderView(0);
    fullName = header.findViewById(R.id.fullNameText);
    fullName.setText(userInformation.getFullName());
    username = header.findViewById(R.id.usernameText);
    username.setText(userInformation.getUsername());


    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    mAppBarConfiguration =
        new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_profile,
                R.id.nav_settings,
                R.id.nav_security)
            .setDrawerLayout(drawer)
            .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.nav_drawer, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
        || super.onSupportNavigateUp();
  }

}
