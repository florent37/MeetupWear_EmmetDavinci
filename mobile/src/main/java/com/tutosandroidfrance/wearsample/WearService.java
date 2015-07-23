package com.tutosandroidfrance.wearsample;

import com.github.florent37.davinci.daemon.DaVinciDaemon;
import com.github.florent37.emmet.Emmet;
import com.github.florent37.emmet.EmmetWearableListenerService;
import com.github.florent37.emmetprotocol.AndroidVersion;
import com.github.florent37.emmetprotocol.SmartphoneProtocol;
import com.github.florent37.emmetprotocol.WearProtocol;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WearService extends EmmetWearableListenerService implements SmartphoneProtocol {

    WearProtocol wearProtocol;

    @Override
    public void onCreate() {
        super.onCreate();

        Emmet.getDefault().setLogEnabled(true);

        Emmet.registerReceiver(SmartphoneProtocol.class, this);
        wearProtocol = Emmet.createSender(WearProtocol.class);
    }

    protected void getAndroidVersions() {

        AndroidService androidService = new RestAdapter.Builder()
                .setEndpoint(AndroidService.ENDPOINT)
                .build().create(AndroidService.class);

        //Récupère et deserialise le contenu de mon fichier JSON en objet List<AndroidVersion>
        androidService.getElements(new Callback<List<AndroidVersion>>() {
            @Override
            public void success(List<AndroidVersion> androidVersions, Response response) {
                wearProtocol.sendRepos("florent37",androidVersions);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        DaVinciDaemon.with(getApplicationContext()).handleMessage(messageEvent);
    }

    @Override
    public void pleaseSendMeRepos() {
        getAndroidVersions();
    }
}
