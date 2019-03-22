package me.tremor.Airglow_user.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.NewsfeedEventFragment;

public class NewsFeedActivity extends AppCompatActivity {

    BottomNavigationView mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
        mMenu = findViewById(R.id.purchase_menu);
        MenuItem mFocused =mMenu.getMenu().getItem(1);
        mFocused.setChecked(true);

        mMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent newIntent;

                switch (menuItem.getItemId()){
                    case R.id.nav_car:
                        newIntent= new Intent(NewsFeedActivity.this, CarpoolingActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.nav_home:
                        newIntent= new Intent(NewsFeedActivity.this, MainActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.nav_news:
                        break;
                    case  R.id.nav_profile:
                        newIntent = new Intent(NewsFeedActivity.this, ProfileActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
                return false;
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_newsfeed, new NewsfeedEventFragment())
                .commit();


    }




}

