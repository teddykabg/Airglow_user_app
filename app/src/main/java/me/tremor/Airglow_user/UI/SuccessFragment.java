package me.tremor.Airglow_user.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import me.tremor.Airglow_user.Activities.PurchaseActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.BuyTicket;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment representing the stripe form
 */
public class SuccessFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.success_fragment, container, false);

        //Card cardToSave = mCardInputWidget.getCard();

        /*if (cardToSave == null || !validateCard(cardToSave)) {
            Toast.makeText(getActivity(), "Carta non valida",
                    Toast.LENGTH_LONG).show();
        }*/

        return view;
    }


}
