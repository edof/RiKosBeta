package com.edo.eno.rikosbeta.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class KostFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Context context;
    private View view;
    ArrayList <KostItem> kostList;
    String[] fasilitasArr = new String[10];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kost, container, false);
        context = container.getContext();
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar_kost);

        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar_favorit);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.home));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.home));
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_KOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressBar.setVisibility(View.GONE);

                        //ArrayList<KostItem> kostItemList = new JsonConverter<KostItem>().toArrayList(response, KostItem.class);

                        kostList = new ArrayList<>();

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String namaKost = jsonObject.getString("nama");
                                String hargaKost = jsonObject.getString("harga");
                                String luasKost = jsonObject.getString("luas");
                                String alamatKost = jsonObject.getString("alamat");
                                String statusKost = jsonObject.getString("status");
                                String desKost = jsonObject.getString("deskripsi");
                                String ownerKost = jsonObject.getString("owner");
                                String teleponKost = jsonObject.getString("telepon");
                                String latKost = jsonObject.getString("latitude");
                                String longKost = jsonObject.getString("longitude");
                                String fotoKost = jsonObject.getString("foto");
                                String bedKost = jsonObject.getString("bed");
                                String kmKost = jsonObject.getString("km_dalam");
                                String almariKost = jsonObject.getString("almari");
                                String mejaKost = jsonObject.getString("meja_belajar");
                                String wifiKost = jsonObject.getString("wifi");
                                String ruangKost = jsonObject.getString("ruang_tamu");
                                String dapurKost = jsonObject.getString("dapur");
                                String kulkasKost = jsonObject.getString("kulkas");
                                String tvKost = jsonObject.getString("tv");
                                String parkirKost = jsonObject.getString("parkir_mobil");

                                Log.e("get deskripsi", desKost);

//                                JSONArray fasilArray = jsonObject.getJSONArray("fasilitas");

//                                for (int j=0; j<fasilArray.length();j++){
//                                    JSONObject fasilObject = fasilArray.getJSONObject(j);
//                                    fasilitasArr[0] = fasilObject.getString("bed");
//                                    fasilitasArr[1] = fasilObject.getString("km_dalam");
//                                    fasilitasArr[2] = fasilObject.getString("almari");
//                                    fasilitasArr[3] = fasilObject.getString("meja_belajar");
//                                    fasilitasArr[4] = fasilObject.getString("wifi");
//                                    fasilitasArr[5] = fasilObject.getString("ruang_tamu");
//                                    fasilitasArr[6] = fasilObject.getString("dapur");
//                                    fasilitasArr[7] = fasilObject.getString("kulkas");
//                                    fasilitasArr[8] = fasilObject.getString("tv");
//                                    fasilitasArr[9] = fasilObject.getString("parkir_mobil");
//                                }

                                KostItem mKost = new KostItem(namaKost, hargaKost, luasKost, alamatKost, statusKost, desKost, ownerKost,teleponKost, latKost, longKost, fotoKost,
                                        bedKost, kmKost, almariKost, mejaKost, wifiKost, ruangKost, dapurKost, kulkasKost, tvKost, parkirKost);
                                kostList.add(mKost);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        KostAdapter kostAdapter = new KostAdapter(context, kostList);
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
