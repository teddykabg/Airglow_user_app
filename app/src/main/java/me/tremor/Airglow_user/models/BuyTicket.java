package me.tremor.Airglow_user.models;

import com.stripe.android.model.Token;

public class BuyTicket {
    int event;//id evento
    int entry_type;
    String payment_token;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getEntry_type() {
        return entry_type;
    }

    public void setEntry_type(int entry_type) {
        this.entry_type = entry_type;
    }

    public String getPayment_token() {
        return payment_token;
    }

    public void setPayment_token(String payment_token) {
        this.payment_token = payment_token;
    }
}
