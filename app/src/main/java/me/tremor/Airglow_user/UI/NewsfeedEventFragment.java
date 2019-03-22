package me.tremor.Airglow_user.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.adapters.NewsEventAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsfeedEventFragment extends Fragment {
    RecyclerView mRecycler;
    public NewsfeedEventFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_newsfeed_event, container, false);
        mRecycler = view.findViewById(R.id.news_event_list);
        NewsEventAdapter mListAdapter = new NewsEventAdapter(getActivity());
        mRecycler.setAdapter(mListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);

        return view;
    }


}
