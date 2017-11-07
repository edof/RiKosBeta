/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.edo.eno.rikosbeta.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.rv.KostItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailKostActivity extends AppCompatActivity {

    private ArrayList<KostItem> mKostItems;
//    private String nama, harga, luas, alamat, deskripsi,
//            status, owner, telepon, latitude, longitude, foto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String nama = getIntent().getStringExtra("namaKost");
        String harga = getIntent().getStringExtra("hargaKost");
        String alamat = getIntent().getStringExtra("alamatKost");
        String luas = getIntent().getStringExtra("luasKost");
        String deskripsi = getIntent().getStringExtra("statusKost");
        String status = getIntent().getStringExtra("deskripsiKost");
        String owner = getIntent().getStringExtra("ownerKost");
        String telepon = getIntent().getStringExtra("teleponKost");
        String latitude = getIntent().getStringExtra("latitudeKost");
        String longitude = getIntent().getStringExtra("longitudeKost");

        String bed = getIntent().getStringExtra("bedKost");
        String km_dalam = getIntent().getStringExtra("kmKost");
        String almari = getIntent().getStringExtra("almariKost");
        String meja_belajar = getIntent().getStringExtra("mejaKost");
        String wifi = getIntent().getStringExtra("wifiKost");
        String ruang_tamu = getIntent().getStringExtra("ruangKost");
        String dapur = getIntent().getStringExtra("dapurKost");
        String kulkas = getIntent().getStringExtra("kulkasKost");
        String tv = getIntent().getStringExtra("tvKost");
        String parkir_mobil = getIntent().getStringExtra("parkirKost");

        Log.e("get status ", status);
        Log.e("get desk: ", deskripsi);
        Log.e("get bed: ", bed);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(nama);

        TextView mBed = (TextView)findViewById(R.id.tv_bed);
        mBed.setText("Kasur: " + bed);
        TextView kmDalam = (TextView) findViewById(R.id.tv_km);
        kmDalam.setText("Kamar Mandi Dalam: " + km_dalam);
        TextView mAlmari = (TextView)findViewById(R.id.tv_almari);
        mAlmari.setText("Almari: " + almari);
        TextView mejaBelajar = (TextView)findViewById(R.id.tv_meja);
        mejaBelajar.setText("Meja Belajar: " + meja_belajar);
        TextView mWifi = (TextView)findViewById(R.id.tv_wifi);
        mWifi.setText("Wi-Fi/ Internet: " + wifi);
        TextView ruangTamu = (TextView)findViewById(R.id.tv_rtamu);
        ruangTamu.setText("Ruang Tamu: " + ruang_tamu);
        TextView mDapur = (TextView)findViewById(R.id.tv_dapur);
        mDapur.setText("Dapur: " + dapur);
        TextView mKulkas = (TextView)findViewById(R.id.tv_kulkas);
        mKulkas.setText("Kulkas: " + kulkas);
        TextView mTv = (TextView)findViewById(R.id.tv_tv);
        mTv.setText("Televisi: " + tv);
        TextView parkir = (TextView)findViewById(R.id.tv_parkir);
        parkir.setText("Parkir Mobil: " + parkir_mobil);

        TextView mHarga = (TextView) findViewById(R.id.detailHarga);
        mHarga.setText("Harga Sewa: Rp " + harga + "/bln");
//        mHarga.setTextColor(ContextCompat.getColor(this, R.color.harga));
        TextView mLuas = (TextView) findViewById(R.id.detailLuas);
        mLuas.setText("Luas: " + luas + " m\u00B2");
        TextView mAlamat = (TextView) findViewById(R.id.detailAlamat);
        mAlamat.setText("Alamat: " + alamat);
        TextView mDeskripsi = (TextView) findViewById(R.id.detailDeskripsi);
        mDeskripsi.setText(deskripsi);
        TextView mStatus = (TextView) findViewById(R.id.detailStatus);
        mStatus.setText(status);
        if(status.equals("1")){
            mStatus.setText("Status Kost: Kamar Tersedia");
//            mStatus.setTextColor(ContextCompat.getColor(this, R.color.ada));
        }else{
            mStatus.setText("Status Kost: Kamar Penuh");
//            mStatus.setTextColor(ContextCompat.getColor(this, R.color.penuh));
        }
        TextView mOwner = (TextView) findViewById(R.id.detailOwner);
        mOwner.setText("CP: " + owner);
        TextView mTelepon = (TextView) findViewById(R.id.detailTelepon);
        mTelepon.setText("No. telp: " + telepon);
        TextView mEpon = (TextView) findViewById(R.id.textTelepon);
//        mEpon.setText(telepon);
        TextView mLatitude = (TextView) findViewById(R.id.textSMS);
//        mLatitude.setText(latitude);
        TextView mNavigasi = (TextView) findViewById(R.id.textNavigasi);
//        mNavigasi.setText(longitude);

        loadBackdrop();
//        getKostInstance();
    }

    private void loadBackdrop() {
        String foto = getIntent().getStringExtra("fotoKost");
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Picasso.with(this)
                .load(foto)
                .into(imageView);
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

    public void Epon(View v) {
        String telepon = getIntent().getStringExtra("teleponKost");
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telepon));
        startActivity(i);
    }

    public void Sms (View v) {
        String sms = getIntent().getStringExtra("teleponKost");
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setType("vnd.android-dir/mms-sms");
        i.setData(Uri.parse("sms:" + sms));
        startActivity(i);
    }

    public void Navigasi (View v){
        String lat = getIntent().getStringExtra("latitudeKost");
        String lng = getIntent().getStringExtra("longitudeKost");
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" +lat + "," + lng);
        Intent i = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        i.setPackage("com.google.android.apps.maps");
//        i.setClassName("com.google.android.apps.maps",
        //"com.google.android.maps.MapsActivity");
        startActivity(i);
    }
}
