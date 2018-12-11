package me.tremor.Airglow_user;



import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.adapters.FeedListAdapter;

import me.tremor.Airglow_user.models.Event;
import me.tremor.Airglow_user.viewmodel.FeedViewModel;

/**
 * Activity representing the Main activity.
 */
public class MainActivity extends AppCompatActivity {




    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecycler = findViewById(R.id.list_feed);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);
        FeedViewModel eventViewModel= ViewModelProviders.of(this).get(FeedViewModel.class);
        ArrayList<Event> listEvent= new ArrayList<Event>();
        /*Event event= new Event(0,5,"MM","MM","GGG");
        for(int i=0;i<1000000;i++){
            listEvent.add(event);
        }
        final FeedListAdapter adapter = new FeedListAdapter(listEvent,this);*/
        final FeedListAdapter adapter = new FeedListAdapter(this);

        mRecycler.setAdapter(adapter);

       // if (AccessToken.getCurrentAccessToken() == null) {
            //vai al fragment di login
        //}
        //eventViewModel = new FeedViewModel();

        /*
         * Step 2: Setup the adapter class for the RecyclerView
         *
         * */
        eventViewModel.getEventPagedList().observe(this, new Observer<PagedList<Event>>() {
            @Override
            public void onChanged(PagedList<Event> events) {
                adapter.submitList(events);
            }
        });



    }


        /*if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, new TransactionFragment())
                    .commit();
        }*/


   /* @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }*/
}
