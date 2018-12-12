package me.tremor.Airglow_user.datasource;

import android.util.Log;

import com.bottlerocketstudios.vault.SharedPreferenceVault;

import java.util.ArrayList;
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
    public List<ShortEvent> list_event =new ArrayList<>();
    boolean has_next = false;
    IdEvent mId;

    final SharedPreferenceVault mSV= VaultLocator.getAutomaticallyKeyedVault();


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ShortEvent> callback) {
        callback.onResult(list_event,null,FIRST_PAGE+1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ShortEvent> callback) {
        //getIds();

        Integer key = (params.key > 1) ? params.key - 1 : null;
        callback.onResult(list_event,key);

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ShortEvent> callback) {
        //getIds();
        Integer key = has_next ? params.key + 1 : null;
        callback.onResult(list_event,key);
    }

  /*  public void getIds(){
        RetrofitClient.getInsance().getApi().fetchIds(mSV.getString(PREF_SECRET,null),).
                enqueue(new Callback<IdEvent>() {
                    @Override
                    public void onResponse(Call<IdEvent> call, Response<IdEvent> response) {
                       if(response.body() !=null)
                            has_next=response.body().isHas_next();
                            getEvents(response.body());
                    }
                    @Override
                    public void onFailure(Call<IdEvent> call, Throwable t) {
                        Log.d("No_ID","niente id");
                    }
                });
    }
    public void getEvents(IdEvent mID){
        list_event=null;
        list_event=new ArrayList<>();

            for (int i = 0; i < mID.getEvents().size(); i++) {
                RetrofitClient.getInsance().getApi().getEventInfo(mSV.getString(PREF_SECRET, null), mID.getEvents().get(i).toString()).
                        enqueue(new Callback<ShortEvent>() {
                            @Override
                            public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {
                                list_event.add(response.body());
                            }

                            @Override
                            public void onFailure(Call<ShortEvent> call, Throwable t) {
                                Log.d("NO_SHORT", "Eventi non aggiunti");
                            }
                        });
            }


    }
    */
}
