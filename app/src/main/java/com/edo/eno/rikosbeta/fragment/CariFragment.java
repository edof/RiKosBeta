package com.edo.eno.rikosbeta.fragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.edo.eno.rikosbeta.R;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class CariFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cari, container, false);

        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar_favorit);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        mToolbar.setBackgroundColor(getResources().getColor(R.color.favorit));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.favorit));
        }
        return view;
    }
}
