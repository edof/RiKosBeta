package com.edo.eno.rikosbeta.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edo.eno.rikosbeta.R;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class CariFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cari, container, false);
        return v;
    }
}
