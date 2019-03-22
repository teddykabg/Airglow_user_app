package me.tremor.Airglow_user.datasource;

import android.util.Log;

import com.bottlerocketstudios.vault.SharedPreferenceVault;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import me.tremor.Airglow_user.models.IdEvent;
import me.tremor.Airglow_user.models.ShortEvent;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShortDataSource extends PageKeyedDataSource<Integer,ShortEvent> {
    public static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;
    private static final String PREF_SECRET = "token";
    public List<ShortEvent> list_event =new LinkedList<>();
    boolean has_next = false;
    IdEvent ids;
    final SharedPreferenceVault mSV= VaultLocator.getAutomaticallyKeyedVault();

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ShortEvent> callback) {
       /* RetrofitClient.getInsance().getApi().fetchIds3(mSV.getString(PREF_SECRET,null)).
                enqueue(new Callback<IdEvent>() {
                    @Override
                    public void onResponse(Call<IdEvent> call, Response<IdEvent> response) {
                        if(response.body() !=null) {
                            Log.d("No",response.body().toString());
                            for (int i = 0; i < response.body().getEvents().size(); i++) {
                                //List<ShortEvent> ak=new ArrayList<>();
                                RetrofitClient.getInsance().getApi().getEventInfoss(mSV.getString(PREF_SECRET, null), response.body().getEvents().get(i).toString()).
                                        enqueue(new Callback<ShortEvent>() {
                                            @Override
                                            public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {
                                                if(response.isSuccessful() && response.body() != null){
                                                    Log.d("event", response.body().toString());

                                                }
                                                else{
                                                    Log.d("event2","body nullo");
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ShortEvent> call, Throwable t) {
                                                Log.d("NO_SHORT", "Eventi non aggiunti");
                                            }
                                        });
                            }

                        }
                    }
                    @Override
                    public void onFailure(Call<IdEvent> call, Throwable t) {
                        Log.d("No_ID","niente id");
                    }
                });
        callback.onResult(ll.getList(),null,2);
        Log.d("OK","ok");
*/
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ShortEvent> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ShortEvent> callback) {
        Log.d("after","here i'm");
        //getIds();

        Integer key = has_next ? params.key + 1 : null;
        callback.onResult(list_event,key);
    }

    public void getIds(){
       /* RetrofitClient.getInsance().getApi().fetchIds3(mSV.getString(PREF_SECRET,null)).
                enqueue(new Callback<IdEvent>() {
                    @Override
                    public void onResponse(Call<IdEvent> call, Response<IdEvent> response) {
                       if(response.body() !=null) {
                           Log.d("No",response.body().toString());
                       }
                    }
                    @Override
                    public void onFailure(Call<IdEvent> call, Throwable t) {
                        Log.d("No_ID","niente id");
                    }
                });*/
    }
    public void getEvents(IdEvent mID){
        //On response non si ricorda nulla
        //passa un array vuoto sopra

        for (int i = 0; i < mID.getEvents().size(); i++) {
            List<ShortEvent> ak=new ArrayList<>();
                RetrofitClient.getInsance().getApi().getEventInfoss(mSV.getString(PREF_SECRET, null), mID.getEvents().get(i).toString()).
                        enqueue(new Callback<ShortEvent>() {
                            @Override
                            public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {

                                if(response.isSuccessful() && response.body() != null){
                                    Log.d("event", response.body().toString());
                                }
                                else{
                                    Log.d("event2","body nullo");
                                }
                            }

                            @Override
                            public void onFailure(Call<ShortEvent> call, Throwable t) {
                                Log.d("NO_SHORT", "Eventi non aggiunti");
                            }
                        });
                if(i== mID.getEvents().size()-1){
                    for(ShortEvent item : ak)
                        Log.d("HH",item.toString());
                }

            }

    }


}
