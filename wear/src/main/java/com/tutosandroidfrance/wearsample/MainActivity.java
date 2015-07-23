package com.tutosandroidfrance.wearsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;

import com.github.florent37.emmet.Emmet;
import com.github.florent37.emmetprotocol.AndroidVersion;
import com.github.florent37.emmetprotocol.SmartphoneProtocol;
import com.github.florent37.emmetprotocol.WearProtocol;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements WearProtocol {

    private GridViewPager pager;
    private DotsPageIndicator dotsPageIndicator;

    //la liste des éléments à afficher
    private List<AndroidVersion> elementList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Emmet.onCreate(this);

        Emmet.getDefault().setLogEnabled(true);

        pager = (GridViewPager) findViewById(R.id.pager);
        dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        dotsPageIndicator.setPager(pager);

        Emmet.registerReceiver(WearProtocol.class, this);

        SmartphoneProtocol smartphoneProtocol = Emmet.createSender(SmartphoneProtocol.class);
        smartphoneProtocol.pleaseSendMeRepos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Emmet.onDestroy();
    }


    //TODO receive androidVersions & startMainScreen

    public void startMainScreen() {
        //penser à effectuer les actions graphiques dans le UIThread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //nous affichons ici dans notre viewpager

                if (pager != null && pager.getAdapter() == null)
                    pager.setAdapter(new ElementGridPagerAdapter(MainActivity.this, elementList, getFragmentManager()));
            }
        });
    }

    @Override
    public void sendRepos(String name, List<AndroidVersion> repos) {
        elementList.addAll(repos);
        startMainScreen();
    }
}
