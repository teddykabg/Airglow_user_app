package me.tremor.Airglow_user.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CarpoolingActivity extends AppCompatActivity {
    BottomNavigationView mMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpooling);

        mMenu = findViewById(R.id.purchase_menu);
        MenuItem mFocused =mMenu.getMenu().getItem(2);
        mFocused.setChecked(true);

        mMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent newIntent;

                switch (menuItem.getItemId()){
                    case R.id.nav_car:

                        break;
                    case R.id.nav_home:
                        newIntent= new Intent(CarpoolingActivity.this, MainActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.nav_news:
                        newIntent= new Intent(CarpoolingActivity.this, NewsFeedActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case  R.id.nav_profile:
                        newIntent = new Intent(CarpoolingActivity.this, ProfileActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
                return false;
            }
        });
    }
}
