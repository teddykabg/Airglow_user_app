package me.tremor.Airglow_user.adapters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import me.tremor.Airglow_user.datasource.ShortDataSource;
import me.tremor.Airglow_user.datasource.factory.ShortDataSourceFactory;
import me.tremor.Airglow_user.models.ShortEvent;

public class ShortViewModel extends ViewModel {
    LiveData<PagedList<ShortEvent>> eventPagedList;
    LiveData<PageKeyedDataSource<Integer,ShortEvent>> liveDataSource;


    public ShortViewModel() {
        ShortDataSourceFactory shortFactory = new ShortDataSourceFactory();
        liveDataSource=shortFactory.getShortLiveDataSource();


        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ShortDataSource.PAGE_SIZE)
                        .build();
        eventPagedList = (new LivePagedListBuilder<Integer,ShortEvent>(shortFactory,config)).build();
    }

    public LiveData<PagedList<ShortEvent>> getEventPagedList() {
        return eventPagedList;
    }

    public void setEventPagedList(LiveData<PagedList<ShortEvent>> eventPagedList) {
        this.eventPagedList = eventPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, ShortEvent>> getLiveDataSource() {
        return liveDataSource;
    }

    public void setLiveDataSource(LiveData<PageKeyedDataSource<Integer, ShortEvent>> liveDataSource) {
        this.liveDataSource = liveDataSource;
    }
}
