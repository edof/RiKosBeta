package com.edo.eno.rikosbeta.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.app.AppConfig;
import com.edo.eno.rikosbeta.app.VolleySingleton;
import com.edo.eno.rikosbeta.rv.KostAdapter;
import com.edo.eno.rikosbeta.rv.KostItem;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class KostFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Context context;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kost, container, false);
        context = container.getContext();
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar_kost);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_KOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressBar.setVisibility(View.GONE);

                        ArrayList<KostItem> kostItemList = new JsonConverter<KostItem>().toArrayList(response, KostItem.class);

                        KostAdapter kostAdapter = new KostAdapter(context, kostItemList);
                        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvKost);
                        mRecyclerView.setHasFixedSize(true);
                        LinearLayoutManager manager = new LinearLayoutManager(context);
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mRecyclerView.setAdapter(kostAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
        );
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
        return view;
    }
}
