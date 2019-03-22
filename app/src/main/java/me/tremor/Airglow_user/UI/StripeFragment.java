package me.tremor.Airglow_user.UI;

import android.app.DatePickerDialog;
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
public class StripeFragment extends Fragment {
    Button mButton;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stripe_fragment, container, false);
        CardInputWidget mCardInputWidget = (CardInputWidget) view.findViewById(R.id.card_input_widget);
        mButton = view.findViewById(R.id.confirm_button);


        //Card cardToSave = mCardInputWidget.getCard();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToken(mCardInputWidget.getCard());
            }
        });
        /*if (cardToSave == null || !validateCard(cardToSave)) {
            Toast.makeText(getActivity(), "Carta non valida",
                    Toast.LENGTH_LONG).show();
        }*/

        return view;
    }

    boolean validateCard(Card mCard){

        return true;
    }
    void sendToken(Card card) {
        Stripe stripe = new Stripe(getContext(), "pk_test_edWxmUMD6TV5lSWYRTV9almk");
        stripe.createToken(card, new TokenCallback() {
                    public void onSuccess(Token token) {
                        TicketBuy mService;
                        mService = new TicketBuy(token.getId(),((PurchaseActivity)getActivity()).getEventId(),
                                ((PurchaseActivity)getActivity()).getModality(),(PurchaseActivity)(getActivity()));
                        mService.execute();


                    }

                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getContext(),
                                "Error!",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );

    }
}

class TicketBuy extends AsyncTask{
    String token;
    SharedPreferenceVault mShared = VaultLocator.getAutomaticallyKeyedVault();
    int eventId;
    int entryId;
    BuyTicket mBuy = new BuyTicket();
    PurchaseActivity mActivity;

    public TicketBuy(String token, int eventId, int entryId,PurchaseActivity mActivity) {
        this.token = token;
        this.eventId = eventId;
        this.entryId = entryId;
        mBuy.setEntry_type(entryId);
        mBuy.setEvent(eventId);
        mBuy.setPayment_token(token);
        this.mActivity=mActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        RetrofitClient.getInsance().getApi().buyTicket(mShared.getString("token",null),mBuy)
        .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200){
                    Log.d("Acquisto","ok perfetto");
                    //TODO:Schermata acquisto effettuato
                    mActivity.doTransitionOk();
                }
                else{
                    Log.d("No","non acquisto"+response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("mai",t.getMessage());
            }
        });
        return null;
    }
}

