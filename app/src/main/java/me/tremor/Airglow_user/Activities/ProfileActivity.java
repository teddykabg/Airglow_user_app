package me.tremor.Airglow_user.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.service.ProfileService;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView mMenu;
    TextView mName;
    TextView mSurname;
    ProfileService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mMenu = findViewById(R.id.my_menu2);

        mName = findViewById(R.id.name_profile);
        mSurname = findViewById(R.id.surname_profile);

        mService = new ProfileService(mName,mSurname);
        mService.execute();
        MenuItem mFocused =mMenu.getMenu().getItem(3);
        mFocused.setChecked(true);

        mMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_car:
                        Intent newIntent1 = new Intent(ProfileActivity.this, CarpoolingActivity.class);
                        startActivity(newIntent1);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.nav_home:
                        Intent newIntent = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(newIntent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                    case R.id.nav_news:
                        Intent Intent = new Intent(ProfileActivity.this, NewsFeedActivity.class);
                        startActivity(Intent);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                }
                return false;
            }
        });


    }

}
