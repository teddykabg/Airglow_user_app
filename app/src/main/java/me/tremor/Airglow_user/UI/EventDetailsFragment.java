package me.tremor.Airglow_user.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.ShortEvent;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventDetailsFragment extends Fragment {
    int eventId;
    ShortEvent event;
    SharedPreferenceVault mShared;
    TextView mTitle;
    TextView mDisco;
    TextView mDescription;
    Button mButton;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.event_details_fragment, container, false);
        eventId= getActivity().getIntent().getExtras().getInt("id");
        event=new ShortEvent();
        Log.d("id","EventId:"+eventId);
        mShared = VaultLocator.getAutomaticallyKeyedVault();
        mTitle = view.findViewById(R.id.title_details);
        mDisco = view.findViewById(R.id.disco_details);
        mDescription = view.findViewById(R.id.event_description);
        mButton = view.findViewById(R.id.purchase1_button);



        RetrofitClient.getInsance().getApi().getEventInfoss(mShared.getString("token",null),Integer.toString(eventId))
                .enqueue(new Callback<ShortEvent>() {
                    @Override
                    public void onResponse(Call<ShortEvent> call, Response<ShortEvent> response) {
                       if(response.body() != null) {
                           mTitle.setText(response.body().getTitle());
                           mDisco.setText(response.body().getDisco().getName());
                           mDescription.setText(response.body().getDescription());
                          // Log.d("lat", "lat" + response.body().getDisco().getLat());
                       }

                    }

                    @Override
                    public void onFailure(Call<ShortEvent> call, Throwable t) {
                        Log.d("NO","Hola"+t.getMessage());
                    }
                });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ModalityFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
               /* Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

        return view;
    }
}
