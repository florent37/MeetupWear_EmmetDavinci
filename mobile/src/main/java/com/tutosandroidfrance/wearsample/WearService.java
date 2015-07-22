package com.tutosandroidfrance.wearsample;

import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WearService extends WearableListenerService { //TODO extends WearableListenerService

    //TODO create a sender

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO initialise receiver this

        //TODO create a sender
    }

    protected void getAndroidVersions() {

        AndroidService androidService = new RestAdapter.Builder()
                .setEndpoint(AndroidService.ENDPOINT)
                .build().create(AndroidService.class);

        //Récupère et deserialise le contenu de mon fichier JSON en objet List<AndroidVersion>
        androidService.getElements(new Callback<List<AndroidVersion>>() {
            @Override
            public void success(List<AndroidVersion> androidVersions, Response response) {

                //TODO send versions to wear
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

    }

    //TODO onMessageReceived DaVinciDaemon handleMessage
}
