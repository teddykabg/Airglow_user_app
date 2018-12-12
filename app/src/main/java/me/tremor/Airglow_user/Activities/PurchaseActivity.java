package me.tremor.Airglow_user.Activities;

import androidx.appcompat.app.AppCompatActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.EventDetailsFragment;

import android.os.Bundle;

public class PurchaseActivity extends AppCompatActivity {
    CharSequence modality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


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
}

