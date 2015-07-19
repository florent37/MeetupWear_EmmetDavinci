package com.tutosandroidfrance.wearsample;

import com.github.florent37.davinci.daemon.DaVinciDaemon;
import com.github.florent37.emmet.Emmet;
import com.github.florent37.emmet.EmmetWearableListenerService;
import com.google.android.gms.wearable.MessageEvent;
import com.tutosandroidfrance.wearprotocol.AndroidVersion;
import com.tutosandroidfrance.wearprotocol.SmartphoneProtocol;
import com.tutosandroidfrance.wearprotocol.WearProtocol;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WearService extends EmmetWearableListenerService implements SmartphoneProtocol {

    private WearProtocol wearProtocol;

    @Override
    public void onCreate() {
        super.onCreate();

        //initialise la récéption de données
        Emmet.registerReceiver(SmartphoneProtocol.class, this);

        //initialise l'envoie de données vers la montre
        wearProtocol = Emmet.createSender(WearProtocol.class);
    }

    //lorsque la montre envoie le message pleaseSendMeVersions au smartphone
    @Override
    public void pleaseSendMeVersions() {
        //Utilise Retrofit pour réaliser un appel REST
        AndroidService androidService = new RestAdapter.Builder()
                .setEndpoint(AndroidService.ENDPOINT)
                .build().create(AndroidService.class);

        //Récupère et deserialise le contenu de mon fichier JSON en objet List<AndroidVersion>
        androidService.getElements(new Callback<List<AndroidVersion>>() {
            @Override
            public void success(List<AndroidVersion> androidVersions, Response response) {

                //envoie cette liste à la montre
                wearProtocol.transferVersions(androidVersions);
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);

        //permet à DaVinciDaemon d'écouter les messages
        DaVinciDaemon.with(getApplicationContext()).handleMessage(messageEvent);
    }
}
