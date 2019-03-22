package me.tremor.Airglow_user.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.EventDetailsFragment;
import me.tremor.Airglow_user.UI.RegisterFragment;
import me.tremor.Airglow_user.UI.StripeFragment;
import me.tremor.Airglow_user.UI.SuccessFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PurchaseActivity extends AppCompatActivity {
    CharSequence modality;
    BottomNavigationView mMenu;
    int modalityId;
    int eventId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        mMenu = findViewById(R.id.purchase_menu);

       // mMenu.setOnNavigationItemSelectedListener(navListener);
        eventId= getIntent().getExtras().getInt("id");
        MenuItem mFocused =mMenu.getMenu().getItem(0);
        mFocused.setChecked(true);

        if (savedInstanceState == null) {
            EventDetailsFragment details = new EventDetailsFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_1, new EventDetailsFragment())
                    .commit();
        }
    }
    public void getModality(CharSequence modality){
        this.modality=modality;
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Activity mSelected = null;
            Intent newIntent;

            switch (menuItem.getItemId()){
                case R.id.nav_car:
                    // mSelected= new CarPoolingActivity();
                    break;
                case R.id.nav_home:
                    newIntent= new Intent(PurchaseActivity.this, ProfileActivity.class);
                    startActivity(newIntent);
                    overridePendingTransition(0, 0);
                    finish();
                    break;
                case R.id.nav_news:
                    //mSelected = new NewsFeedActivity();
                    break;
                case  R.id.nav_profile:
                    newIntent = new Intent(PurchaseActivity.this, ProfileActivity.class);
                    startActivity(newIntent);
                    overridePendingTransition(0, 0);
                    finish();
                    break;
            }
            return false;
        }
    };

    public void doTransition(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_1, new StripeFragment())
                .addToBackStack(null)
                .commit();
    }
    public void doTransitionOk(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_1, new SuccessFragment())
                .addToBackStack(null)
                .commit();
    }

    public void saveModality(int id){
        modalityId=id;
    }
    public int  getModality(){
        return modalityId;
    }
    public int  getEventId(){
        return eventId;
    }

}

