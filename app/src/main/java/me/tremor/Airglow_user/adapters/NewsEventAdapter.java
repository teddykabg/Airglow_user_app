package me.tremor.Airglow_user.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.NewsfeedFragment;


public class NewsEventAdapter extends RecyclerView.Adapter {
    FragmentActivity mActivity;
    public NewsEventAdapter(FragmentActivity mActivity) {
        this.mActivity = mActivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_card,parent,false);

        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ReservationViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class ReservationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView mCard;
        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = itemView.findViewById(R.id.event_card);
            mCard.setOnClickListener(this);
        }
        public void bindView(int position){

        }

        @Override
        public void onClick(View v) {
            Fragment newFragment = new NewsfeedFragment();
            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_newsfeed, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
    }
}
