package me.tremor.Airglow_user.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.ShortEvent;

public class EventAdapter extends PagedListAdapter<ShortEvent,EventAdapter.EventViewHolder> {

    private Context context;

    public EventAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context=context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.short_info_feed,parent,false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        ShortEvent item= getItem(position);

        if(item != null){
          //  holder.mDisco.setText(item.getDisco());
            holder.mTitle.setText(item.getTitle());
            holder.mId.setText(item.getId());
        }else{
            Toast.makeText(context, "anche oggi un pollo", Toast.LENGTH_LONG).show();
        }

    }

    private static DiffUtil.ItemCallback<ShortEvent> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ShortEvent>() {
                @Override
                public boolean areItemsTheSame(ShortEvent oldItem, ShortEvent newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(ShortEvent oldItem, ShortEvent newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView mDisco;
        TextView mId;
        TextView mTitle;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mDisco= itemView.findViewById(R.id.mDisco);
            mId= itemView.findViewById(R.id.mId);
            mTitle= itemView.findViewById(R.id.mTitle);
        }
    }
}
