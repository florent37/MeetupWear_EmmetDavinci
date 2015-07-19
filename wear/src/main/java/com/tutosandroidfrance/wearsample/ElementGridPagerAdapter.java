package com.tutosandroidfrance.wearsample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import com.github.florent37.davinci.DaVinci;
import com.tutosandroidfrance.wearprotocol.AndroidVersion;

import java.util.ArrayList;
import java.util.List;

public class ElementGridPagerAdapter extends FragmentGridPagerAdapter {

    private Context context;
    private List<AndroidVersion> androidVersions;
    private List<Row> rows;

    public ElementGridPagerAdapter(Context context, List<AndroidVersion> androidVersions, FragmentManager fm) {
        super(fm);

        this.context = context;
        this.androidVersions = androidVersions;
        this.rows = new ArrayList<Row>();

        //Construit le tableau des éléménts à afficher
        for (AndroidVersion version : androidVersions) {
            rows.add(new Row(
                            //pour l'instant nous ne mettrons qu'un élément par ligne
                            CardFragment.create(version.getTitre(), version.getDescription())
                    )
            );
        }
    }

    //Le fragment à afficher
    @Override
    public Fragment getFragment(int row, int col) {
        return rows.get(row).getColumn(col);
    }

    //le drawable affichée en background pour la ligne [row]
    @Override
    public Drawable getBackgroundForRow(final int row) {
        return DaVinci.with(context).load(this.androidVersions.get(row).getUrl()).into(this, row);
    }

    @Override
    public Drawable getBackgroundForPage(final int row, final int column) {
        //nous pouvons spécifier un drawable différent pour le swipe horizontal
        return BACKGROUND_NONE;
    }

    //Le nombre de lignes dans la grille
    @Override
    public int getRowCount() {
        return rows.size();
    }

    //Le nombre de colonnes par ligne
    @Override
    public int getColumnCount(int rowNum) {
        return rows.get(rowNum).getColumnCount();
    }

}