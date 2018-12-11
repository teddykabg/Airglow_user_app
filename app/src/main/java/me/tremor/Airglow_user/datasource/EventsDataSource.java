package me.tremor.Airglow_user.datasource;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.q42.qlassified.Qlassified;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.adapters.FeedListAdapter;
import me.tremor.Airglow_user.models.Event;
import me.tremor.Airglow_user.models.Events_id;
import me.tremor.Airglow_user.service.UserClient;
import me.tremor.Airglow_user.utils.NetworkState;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsDataSource  extends PageKeyedDataSource<Integer, Event> {
    Retrofit.Builder builder;
    Retrofit mRetrofit;
    UserClient userClient;
    final int FIRST_PAGE =1;
    final int PAGE_SIZE=5;
    String Token;
    ArrayList<Integer> events_ids=new ArrayList<>();
    ArrayList<Event> event_list=new ArrayList<Event>();
    private static final String PREF_SECRET = "token";
    final SharedPreferenceVault sharedPreferenceVault = VaultLocator.getAutomaticallyKeyedVault();

   private String CIELO="http://api.airglow.me:5000/v1/";
   private String TERRA="http://192.168.1.35:5000/v1/";
    Context context;

    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public EventsDataSource(Context context) {
        builder = new Retrofit.Builder().baseUrl(CIELO)
                .addConverterFactory(GsonConverterFactory.create());
        mRetrofit = builder.build();
        userClient = mRetrofit.create(UserClient.class);

        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        Token=sharedPreferenceVault.getString(PREF_SECRET, null);
        Log.d("token",Token);

    }
    public EventsDataSource() {
        builder = new Retrofit.Builder().baseUrl(CIELO)
                .addConverterFactory(GsonConverterFactory.create());
        mRetrofit = builder.build();
        userClient = mRetrofit.create(UserClient.class);

        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
        Token=sharedPreferenceVault.getString(PREF_SECRET, null);
        Log.d("token",Token);


    }


    protected String loadSecret() {

        if (sharedPreferenceVault != null) {
            String secretValue = null;
            return secretValue = sharedPreferenceVault.getString(PREF_SECRET, null);
        }
        else{
            return  null;
        }
    }
    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Event> callback) {
            Token=sharedPreferenceVault.getString(PREF_SECRET, null);
            Log.d("token",Token);
            Call<Events_id> call = userClient.fetchIds(Token);

            call.enqueue(new Callback<Events_id>() {
                @Override
                public void onResponse(Call<Events_id> call, Response<Events_id> response) {
                    if(response.isSuccessful()){
                        Events_id a= response.body();
                        a = populateArray(response.body());
                        callback.onResult(a.getEventss(), null, FIRST_PAGE+1);
                        Log.d("ciao","Sono qui");
                    }
                    else{
                        Log.d("riprova","riprova");
                    }
                }

                @Override
                public void onFailure(Call<Events_id> call, Throwable t) {
                    Log.d("riprova","riprova");
                    String errorMessage = t == null ? "unknown error" : t.getMessage();

                }
            });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Event> callback) {
        Call<Events_id> call = userClient.fetchIds(Token);

        call.enqueue(new Callback<Events_id>() {
            @Override
            public void onResponse(Call<Events_id> call, Response<Events_id> response) {
                if(response.isSuccessful()){
                    Events_id a= response.body();

                    a = populateArray(response.body());//Funzione che popola l'array @event_list di eventi, dato un array di id
                    Integer nextKey = params.key>1 ? params.key-1 : null;
                    callback.onResult(a.getEventss(), nextKey);
                }
                else{

                    Log.d("riprova","riprova");
                }
            }

            @Override
            public void onFailure(Call<Events_id> call, Throwable t) {
                Log.d("riprova","riprova");
                String errorMessage = t == null ? "unknown error" : t.getMessage();
            }
        });
    }
    /*
     * This method it is responsible for the subsequent call to load the data page wise.
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Event> callback) {
        Call<Events_id> call = userClient.fetchIds(Token);

        call.enqueue(new Callback<Events_id>() {
            @Override
            public void onResponse(Call<Events_id> call, Response<Events_id> response) {
                if(response.isSuccessful()){
                    Events_id a= response.body();
                    a = populateArray(response.body());
                    Integer nextKey = (response.body().isHas_next()) ? params.key+1 : null;
                    callback.onResult(a.getEventss(), nextKey);

                }
                else{
                    Log.d("riprova","riprova");
                }
            }

            @Override
            public void onFailure(Call<Events_id> call, Throwable t) {
                Log.d("riprova","riprova");
                String errorMessage = t == null ? "unknown error" : t.getMessage();
            }
        });
        //Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);


        //userClient.getEventShortInfo().enqueue(new Callback<Feed>()
    }
    public Events_id populateArray(Events_id events_ids){
        for(int i=0;i<events_ids.getEvents().size();i++){
            //per ogni id presente nell'array @events_id
            Call<Event> call2 = userClient.getEventInfo(Token,events_ids.getEvents().get(i).toString());//passa il token e l'id del evento.
            call2.enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    events_ids.getEventss().add(response.body());

                    if(response.body() != null)
                        Log.d("evento",response.body().toString());
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Log.d("pop","popolamento fallito");
                }
            });
        }
        return events_ids;
    }
   /* public void populateArray(ArrayList<Integer> events_ids){
        for(int i=0;i<events_ids.size();i++){
           //per ogni id presente nell'array @events_id
            Call<Event> call2 = userClient.getEventInfo(Token,events_ids.get(i).toString());//passa il token e l'id del evento.
            call2.enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    event_list.add(response.body());

                    if(response.body() != null)
                    Log.d("evento",response.body().toString());
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Log.d("pop","popolamento fallito");
                }
            });
        }
    }*/
}
