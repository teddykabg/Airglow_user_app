package me.tremor.Airglow_user.UI;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.Activities.PurchaseActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.ShortEvent;
import me.tremor.Airglow_user.service.ModalityAdapter;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ModalityFragment extends Fragment {
    int eventId;
    RecyclerView mModalityList;
    ModalityAdapter mAdapter;

    SharedPreferenceVault mShared = VaultLocator.getAutomaticallyKeyedVault();


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modality_fragment, container, false);
        eventId= getActivity().getIntent().getExtras().getInt("id");

        mModalityList= view.findViewById(R.id.list_items);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mModalityList.setLayoutManager(manager);
        //SetModality mModality = new SetModality(eventId,mAdapter,mModalityList,(PurchaseActivity)getActivity());
        //mModalityList.setAdapter(mAdapter);
        // mModality.execute();
        RetrofitClient.getInsance().getApi().getEventInfoss(mShared.getString("token",null),Integer.toString(eventId))
                .enqueue(new Callback<ShortEvent>() {
                    @Override
                    public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {

                        if(response.body() != null) {
                            mModalityList.setAdapter(new ModalityAdapter(response.body().getEntry_types(),(PurchaseActivity)getActivity()));

                        }
                    }
                    @Override
                    public void onFailure(Call<ShortEvent> call, Throwable t) {
                        Log.d("NO","Hola"+t.getMessage());
                    }
                });




                /*Fragment fragment = new StripeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

        return view;
    }
    public void doTransition(){
        Fragment fragment = new StripeFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_1, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

class SetModality extends AsyncTask{
    int eventId;
    SharedPreferenceVault mShared = VaultLocator.getAutomaticallyKeyedVault();
    ModalityAdapter mAdapter;
    PurchaseActivity mActivity;

    public SetModality(int eventsId,ModalityAdapter mAdapter,RecyclerView mRecycler,PurchaseActivity mActivity) {
        this.eventId = eventsId;
        this.mAdapter = mAdapter;
        this.mActivity= mActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        RetrofitClient.getInsance().getApi().getEventInfoss(mShared.getString("token",null),Integer.toString(eventId))
                .enqueue(new Callback<ShortEvent>() {
                    @Override
                    public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {

                        if(response.body() != null) {
                            mAdapter = new ModalityAdapter(response.body().getEntry_types(),mActivity);
                        }
                    }
                    @Override
                    public void onFailure(Call<ShortEvent> call, Throwable t) {
                        Log.d("NO","Hola"+t.getMessage());
                    }
                });
        return mAdapter;
    }
}

