package me.tremor.Airglow_user.datasource.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import me.tremor.Airglow_user.datasource.ShortDataSource;
import me.tremor.Airglow_user.models.ShortEvent;

public class ShortDataSourceFactory extends DataSource.Factory {
    public MutableLiveData<PageKeyedDataSource<Integer, ShortEvent>> itemLiveDataSource = new MutableLiveData<>();

    public ShortDataSource create() {
        ShortDataSource itemDataSource = new ShortDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ShortEvent>> getShortLiveDataSource() {
        return itemLiveDataSource;
    }
}
