package com.tutosandroidfrance.wearsample;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Représentation d'une ligne - Contient une liste de fragments
 */
public class Row {
    final List<Fragment> columns = new ArrayList<Fragment>();

    public Row(Fragment... fragments) {
        for (Fragment f : fragments) {
            add(f);
        }
    }

    public void add(Fragment f) {
        columns.add(f);
    }

    Fragment getColumn(int i) {
        return columns.get(i);
    }

    public int getColumnCount() {
        return columns.size();
    }
}
