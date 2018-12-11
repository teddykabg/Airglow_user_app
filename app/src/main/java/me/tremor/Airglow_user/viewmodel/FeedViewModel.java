package me.tremor.Airglow_user.viewmodel;



import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import me.tremor.Airglow_user.datasource.EventsDataSource;
import me.tremor.Airglow_user.datasource.factory.EventsFactory;
import me.tremor.Airglow_user.models.Event;
import me.tremor.Airglow_user.utils.NetworkState;

public class FeedViewModel extends ViewModel {
    LiveData<PagedList<Event>> eventPagedList;
    LiveData<PageKeyedDataSource<Integer,Event>> liveDataSource;


    public FeedViewModel() {
        EventsFactory mFactory = new EventsFactory();
        liveDataSource =mFactory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder()).
                                    setEnablePlaceholders(false).
                                        setPageSize(5).build();
        eventPagedList = (new LivePagedListBuilder(mFactory,config)).build();

    }
    public LiveData<PagedList<Event>> getEventPagedList() {
        return eventPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Event>> getLiveDataSource() {
        return liveDataSource;
    }

}
