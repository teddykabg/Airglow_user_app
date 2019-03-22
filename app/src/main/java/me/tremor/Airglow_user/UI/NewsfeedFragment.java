package me.tremor.Airglow_user.UI;


import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.adapters.NewsEventAdapter;
import me.tremor.Airglow_user.adapters.NewsfeedAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.CollapsingToolbarLayout;


public class NewsfeedFragment extends Fragment {

    RecyclerView mRecycler;
    public NewsfeedFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
       // getActivity().setSupportActionBar(toolbar);
        mRecycler = view.findViewById(R.id.feed_list);
        getActivity().setTitle("Nome evento");
        NewsfeedAdapter mListAdapter = new NewsfeedAdapter(getActivity());
        mRecycler.setAdapter(mListAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(layoutManager);
        return view;
    }

}
