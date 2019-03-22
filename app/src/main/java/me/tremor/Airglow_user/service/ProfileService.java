package me.tremor.Airglow_user.service;

import android.os.AsyncTask;
import android.widget.TextView;

import com.bottlerocketstudios.vault.SharedPreferenceVault;

import me.tremor.Airglow_user.models.Profile;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileService extends AsyncTask {
    TextView name;
    TextView surname;
    final SharedPreferenceVault mSV= VaultLocator.getAutomaticallyKeyedVault();
    public ProfileService(TextView name, TextView surname) {
        this.name= name;
        this.surname= surname;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        RetrofitClient.getInsance().getApi().getProfile(mSV.getString("token",null)).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                name.setText(response.body().getFirst_name());
                surname.setText(response.body().getLast_name());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
        return "";
    }
}
