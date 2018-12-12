package me.tremor.Airglow_user.Activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.LoginFragment;
import me.tremor.Airglow_user.service.NavigationHost;
import me.tremor.Airglow_user.vault.VaultLocator;

import android.content.pm.PackageInfo;
import android.os.Bundle;

/**
 * Activity representing the Onboarding screens.
 */

public class OnboardingActivity extends AppCompatActivity implements NavigationHost {
    PackageInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        VaultLocator.initializeVaults(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

   @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
