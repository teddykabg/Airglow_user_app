package me.tremor.Airglow_user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Fragment representing the stripe form
 */
public class StripeFragment extends Fragment {

    DatePickerDialog.OnDateSetListener mDateListener;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stripe_fragment, container, false);
        CardInputWidget mCardInputWidget = (CardInputWidget) view.findViewById(R.id.card_input_widget);

        Card cardToSave = mCardInputWidget.getCard();
        if (cardToSave == null || !validateCard(cardToSave)) {
            Toast.makeText(getActivity(), "Carta non valida",
                    Toast.LENGTH_LONG).show();
        }

        return view;
    }

    boolean validateCard(Card mCard){
        return true;
    }
    void sendToken(Card card){
        Stripe stripe = new Stripe(getContext(), "pk_test_edWxmUMD6TV5lSWYRTV9almk");
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
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
