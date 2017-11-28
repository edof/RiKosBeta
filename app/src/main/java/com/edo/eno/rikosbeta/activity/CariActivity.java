package com.edo.eno.rikosbeta.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.app.AppConfig;
import com.edo.eno.rikosbeta.app.AppController;
import com.edo.eno.rikosbeta.app.VolleySingleton;
import com.edo.eno.rikosbeta.rv.KostAdapter;
import com.edo.eno.rikosbeta.rv.KostItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CariActivity extends AppCompatActivity {
    String status, jenis, harga_min, harga_max, bed, almari, kamar, meja, wifi, ruang_tamu, dapur, ac, tv, parkir;
    ProgressBar mProgressBar;
    ImageView mImageView;
    TextView hasilCari;
    ArrayList<KostItem> kostList;
    private Context context;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        status = getIntent().getStringExtra("status");
        jenis = getIntent().getStringExtra("jenis");
        harga_min = getIntent().getStringExtra("harga_min");
        harga_max = getIntent().getStringExtra("harga_max");
        bed = getIntent().getStringExtra("bed");
        almari = getIntent().getStringExtra("almari");
        kamar = getIntent().getStringExtra("km_dalam");
        meja = getIntent().getStringExtra("meja_belajar");
        wifi = getIntent().getStringExtra("wifi");
        ruang_tamu = getIntent().getStringExtra("ruang_tamu");
        dapur = getIntent().getStringExtra("dapur");
        ac = getIntent().getStringExtra("ac");
        tv = getIntent().getStringExtra("tv");
        parkir = getIntent().getStringExtra("parkir");
//        Log.e("status", bed);
//        Log.e("wifi", wifi);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_kost);
        hasilCari = (TextView) findViewById(R.id.tv_cari);
        mImageView = (ImageView) findViewById(R.id.ivSad);

        cariKost(status, jenis, harga_min, harga_max, bed, almari, kamar, meja, wifi, ruang_tamu, dapur, ac, tv, parkir);

    }

    private void cariKost(final String status, final String jenis, final String harga_min, final String harga_max,
                          final String bed, final String almari, final String kamar, final String meja,
                          final String wifi, final String ruang_tamu, final String dapur, final String ac,
                          final String tv, final String parkir) {

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_CARI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mProgressBar.setVisibility(View.GONE);
                kostList = new ArrayList<>();

                try {

                    if (!response.equals("not found")) {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String idKost = jsonObject.getString("id_kost");
                            String namaKost = jsonObject.getString("nama");
                            String hargaKost = jsonObject.getString("harga");
                            String luasKost = jsonObject.getString("luas");
                            String alamatKost = jsonObject.getString("alamat");
                            String desKost = jsonObject.getString("deskripsi");
                            String statusKost = jsonObject.getString("status");
                            String jenisKost = jsonObject.getString("jenis");
                            String tipeKost = jsonObject.getString("tipe");
                            String ownerKost = jsonObject.getString("owner");
                            String teleponKost = jsonObject.getString("telepon");
                            String latKost = jsonObject.getString("latitude");
                            String longKost = jsonObject.getString("longitude");
                            String gambarKost = jsonObject.getString("nama_gambar");
                            String bedKost = jsonObject.getString("bed");
                            String kmKost = jsonObject.getString("km_dalam");
                            String almariKost = jsonObject.getString("almari");
                            String mejaKost = jsonObject.getString("meja_belajar");
                            String wifiKost = jsonObject.getString("wifi");
                            String ruangKost = jsonObject.getString("ruang_tamu");
                            String dapurKost = jsonObject.getString("dapur");
                            String acKost = jsonObject.getString("ac");
                            String tvKost = jsonObject.getString("tv");
                            String parkirKost = jsonObject.getString("parkir_mobil");

                            Log.e("get parkir", parkirKost);

                            KostItem mKost = new KostItem(idKost, namaKost, hargaKost, luasKost, alamatKost, desKost, statusKost, jenisKost, tipeKost, ownerKost, teleponKost, latKost, longKost, gambarKost,
                                    bedKost, kmKost, almariKost, mejaKost, wifiKost, ruangKost, dapurKost, acKost, tvKost, parkirKost);
                            kostList.add(mKost);
                        }
                    } else {
                        hasilCari.setVisibility(View.VISIBLE);
                        mImageView.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                KostAdapter kostAdapter = new KostAdapter(context, kostList);
                mRecyclerView = (RecyclerView) findViewById(R.id.rvKost);
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
                        Toast.makeText(getApplicationContext(), "String Req Error", Toast.LENGTH_LONG);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status", status);
                params.put("jenis", jenis);
                params.put("harga_awal", harga_min);
                params.put("harga_akhir", harga_max);
                params.put("bed", bed);
                params.put("km_dalam", kamar);
                params.put("almari", almari);
                params.put("meja_belajar", meja);
                params.put("wifi", wifi);
                params.put("ruang_tamu", ruang_tamu);
                params.put("dapur", dapur);
                params.put("ac", ac);
                params.put("tv", tv);
                params.put("parkir_mobil", parkir);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //untuk membuat panah di toolbar kembali ke halaman awal
    //id dari panah default "home"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
