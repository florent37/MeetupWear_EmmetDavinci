package com.tutosandroidfrance.wearsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private GridViewPager pager;
    private DotsPageIndicator dotsPageIndicator;

    //la liste des éléments à afficher
    private List<AndroidVersion> elementList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO initialise Emmet

        pager = (GridViewPager) findViewById(R.id.pager);
        dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        dotsPageIndicator.setPager(pager);

        //TODO initialise Receiver this

        //TODO initialise Sender & send pleaseSendMeVersions
    }

    //TODO destroy Emmet

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

}
