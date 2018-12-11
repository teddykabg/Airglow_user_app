package me.tremor.Airglow_user.datasource.factory;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import me.tremor.Airglow_user.datasource.EventsDataSource;
import me.tremor.Airglow_user.models.Event;

public class EventsFactory extends DataSource.Factory {

    //private MutableLiveData<EventsDataSource> mutableLiveData;
    private MutableLiveData<PageKeyedDataSource<Integer,Event>> mutableLiveData= new MutableLiveData<>();
    private EventsDataSource eventsDataSource;

    @Override
    public DataSource create() {
        eventsDataSource = new EventsDataSource();
        mutableLiveData.postValue(eventsDataSource);
        return eventsDataSource;
    }


    /*public MutableLiveData<EventsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }*/
    public MutableLiveData<PageKeyedDataSource<Integer,Event>> getMutableLiveData(){
        return mutableLiveData;
    }
}
