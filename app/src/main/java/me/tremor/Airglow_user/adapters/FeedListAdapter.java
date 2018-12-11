package me.tremor.Airglow_user.adapters;


import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;

import me.tremor.Airglow_user.models.Event;
import me.tremor.Airglow_user.utils.NetworkState;

public class FeedListAdapter extends PagedListAdapter<Event, RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Event> events;

    public FeedListAdapter(Context context) {
        super(Event.DIFF_CALLBACK);
        this.context = context;
    }
    public FeedListAdapter(ArrayList<Event> events,Context context) {
        super(Event.DIFF_CALLBACK);
        this.context = context;
        this.events=events;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
            this.context=parent.getContext();
            return new EventItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EventItemViewHolder) {
          ((EventItemViewHolder)holder).bindTo(getItem(position));
            if(getItem(position)!=null) {
                Log.d("NO", "Pollooo");
                Log.d("TT",getItem(position).toString());
            }
            if(events != null)
            ((EventItemViewHolder)holder).bindTo(events.get(position));

    }
    }

    @Override
    public int getItemCount() {
        if(events != null)
            return events.size();
        else
            return 0;
    }

    public class EventItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mDesc;
        //fondamentalmente è il feed che viene riempito
        public EventItemViewHolder(View view) {
            super(view);

            mTitle= view.findViewById(R.id.item_title);
            mDesc= view.findViewById(R.id.item_desc);

        }

        public void bindTo(Event event) {
            mDesc.setVisibility(View.VISIBLE);
           Log.d("hi","here i'am");
            mTitle.setText(event.getTitle());
            mDesc.setText(event.getDescription());
        }
    }

}
