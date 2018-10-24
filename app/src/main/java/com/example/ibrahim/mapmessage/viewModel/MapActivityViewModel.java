package com.example.ibrahim.mapmessage.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.ibrahim.mapmessage.network.pojo.Feed;
import com.example.ibrahim.mapmessage.network.pojo.ResponseData;
import com.example.ibrahim.mapmessage.repository.AppRepository;


public class MapActivityViewModel extends AndroidViewModel {


    private AppRepository repository;

    public MapActivityViewModel(@NonNull Application application) {
        super(application);
        repository = AppRepository.getInstance(application);
    }

    public LiveData<ResponseData> getResponse() {

        return repository.getRemoteMessages();
    }


}
