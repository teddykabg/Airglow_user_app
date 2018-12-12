package me.tremor.Airglow_user;



import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.Item;
import me.tremor.Airglow_user.ItemAdapter;
import me.tremor.Airglow_user.ItemViewModel;

/**
 * Activity representing the Main activity.
 */
public class MainActivity extends AppCompatActivity {


    LinearLayoutManager manager;
    BottomNavigationView mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecycler = findViewById(R.id.list_feed);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        mRecycler.setLayoutManager(manager);

        mMenu= findViewById(R.id.my_menu);

        /*ShortViewModel mModel = ViewModelProviders.of(this).get(ShortViewModel.class);
        final EventAdapter adapter = new EventAdapter(this);
        mModel.getEventPagedList().observe(this, new Observer<PagedList<ShortEvent>>() {
            @Override
            public void onChanged(PagedList<ShortEvent> shortEvents) {
                adapter.submitList(shortEvents);
                    Log.d("io",shortEvents.toString());

                Log.d("Mai","Mai qui");
            }
        });
*/
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final ItemAdapter adapter = new ItemAdapter(this);

        itemViewModel.getItemPagedList().observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                adapter.submitList(items);
            }
        });
        mRecycler.setAdapter(adapter);

      /* private BottomNavigationView.OnNavigationItemSelectedListener navListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment mSelected = null;

                switch (menuItem.getItemId()){
                    case R.id.nav_car:
                        mSelected= new CarPoolingActivity();
                        break;
                    case R.id.nav_home:
                        mSelected= new MainActivity();
                        ecc
                }
                return false;
            }
        }*/

    }

}
