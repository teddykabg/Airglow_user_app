package me.tremor.Airglow_user;


import android.util.Log;

import com.bottlerocketstudios.vault.SharedPreferenceVault;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import me.tremor.Airglow_user.models.IdEvent;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 50;
    private static final String FIRST_PAGE = "1";
    private static final String SITE_NAME = "stackoverflow";
    private static RetrofitClient mInstance;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.airglow.me:5000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
            .build();
    UserClient userClient = retrofit.create(UserClient.class);
    final SharedPreferenceVault mSV= VaultLocator.getAutomaticallyKeyedVault();

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

      userClient.fetchIds()
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if(response.body() != null){
                            Log.d("OI" ,"firstget: "+response.body().has_next+" EVENTS: " + response.body().events);

                            callback.onResult(response.body().events, null, 2);

                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });


    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        userClient.fetchIds()
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            Log.d("OI" ,"load before: "+response.body().has_next);
                            callback.onResult(response.body().events, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });


    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

                Log.d("OI" ,"LOADAFTER STARTED");
        userClient.fetchIds()
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                        if(response.body() != null){
                            Integer key = response.body().has_next ? params.key + 1 : null;
                            Log.d("OI" ,"onResponse: "+response.body().has_next);
                            callback.onResult(response.body().events, key);
                        }
                        else {
                            Log.d("OI" ,response.toString());
                            Log.d("OI" ,"BODY IS NULL with key " + params.key);
                        }

                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.d("Off" ,"on");
                    }
                });

    }
}
