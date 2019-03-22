package me.tremor.Airglow_user.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.UI.NewsfeedFragment;


public class NewsfeedAdapter extends RecyclerView.Adapter {
    FragmentActivity mActivity;
    public NewsfeedAdapter(FragmentActivity mActivity) {
        this.mActivity = mActivity;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_feed,parent,false);

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

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bindView(int position){

        }

        @Override
        public void onClick(View v) {


        }
    }
}
