package com.example.ibrahim.mapmessage.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.ibrahim.mapmessage.network.RetrofitClient;
import com.example.ibrahim.mapmessage.network.pojo.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {

    private static AppRepository ourInstance;
    public LiveData<ResponseData> mEntries;
    private Context context;


    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        this.context = context;
        mEntries = getRemoteMessages();
    }


    public LiveData<ResponseData> getRemoteMessages() {
        final MutableLiveData<ResponseData> data = new MutableLiveData<>();
        RetrofitClient.getClient().fetchMessages()
                .enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {

                    }
                });
        return data;
    }

}
