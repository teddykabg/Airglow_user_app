package me.tremor.Airglow_user.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.tremor.Airglow_user.Activities.PurchaseActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.ShortEvent;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ModalityFragment extends Fragment {
    int eventId;
    MaterialCardView mModality1;
    MaterialCardView mModality2;
    MaterialCardView mModality3;
    TextView mModalityText1;
    TextView mModalityText2;
    TextView mModalityText3;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modality_fragment, container, false);
        eventId= getActivity().getIntent().getExtras().getInt("id");

        mModality1 = view.findViewById(R.id.modality1);
        mModality2 = view.findViewById(R.id.modality2);
        mModality3 = view.findViewById(R.id.modality3);

        mModalityText1 = view.findViewById(R.id.modality1_text);
        mModalityText2 = view.findViewById(R.id.modality2_text);
        mModalityText3= view.findViewById(R.id.modality3_text);



        mModality1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new StripeFragment();
                PurchaseActivity mActivity= ((PurchaseActivity)getActivity());

                mActivity.getModality(mModalityText1.getText());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mModality2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new StripeFragment();
                PurchaseActivity mActivity= ((PurchaseActivity)getActivity());

                mActivity.getModality(mModalityText2.getText());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        mModality3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new StripeFragment();
                PurchaseActivity mActivity= ((PurchaseActivity)getActivity());

                mActivity.getModality(mModalityText3.getText());

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
