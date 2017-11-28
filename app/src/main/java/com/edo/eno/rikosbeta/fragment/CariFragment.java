package com.edo.eno.rikosbeta.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.activity.CariActivity;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class CariFragment extends Fragment {

    AlertDialog alertDialog1, alertDialog2, alertDialog3, alertDialog4;
    TextView tvAvailability, tvJenis;
    String status, tvAwal, tvAkhir, bed, almari, kamar, meja, wifi, ruang_tamu, dapur, ac, tv, parkir;
    Button cari;
    CheckBox cbBed, cbKamar, cbAlmari, cbMeja, cbWifi, cbRuangtamu, cbDapur, cbAc, cbTv, cbParkir;
    CharSequence[] sedia = {"Semua", "kamar tersedia"};
    CharSequence[] jenis = {"Harian", "Mingguan", "Bulanan"};

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
        status = "kosong";
        bed = "kosong";
        almari = "kosong";
        kamar = "kosong";
        meja = "kosong";
        wifi = "kosong";
        ruang_tamu = "kosong";
        dapur = "kosong";
        ac = "kosong";
        tv = "kosong";
        parkir = "kosong";
        tvAvailability = (TextView) view.findViewById(R.id.tv_ketersediaan);
        tvAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogStatus();
            }
        });

        tvJenis = (TextView) view.findViewById(R.id.tv_jenis);
        tvJenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogJenis();
            }
        });

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) view.findViewById(R.id.rangeSeekbar3);

        // get min and max text view
        final TextView tvMin = (TextView) view.findViewById(R.id.tv_harga_min);
        final TextView tvMax = (TextView) view.findViewById(R.id.tv_harga_max);
        tvAwal = "100000";
        tvAkhir = "1500000";

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.e("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
                tvAwal = String.valueOf(minValue);
                tvAkhir = String.valueOf(maxValue);
            }
        });

        cbBed = (CheckBox) view.findViewById(R.id.tv_bed);
        cbBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBed.isChecked()){
                    bed = "ada";
                } else {
                    bed = "kosong";
                }
            }
        });
        cbAlmari = (CheckBox) view.findViewById(R.id.tv_almari);
        cbAlmari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAlmari.isChecked()){
                    almari = "ada";
                } else {
                    almari = "kosong";
                }
            }
        });
        cbKamar = (CheckBox) view.findViewById(R.id.tv_km);
        cbKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbKamar.isChecked()){
                    kamar = "ada";
                } else {
                    kamar = "kosong";
                }
            }
        });
        cbMeja = (CheckBox) view.findViewById(R.id.tv_meja);
        cbMeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbMeja.isChecked()){
                    meja = "ada";
                } else {
                    meja = "kosong";
                }
            }
        });
        cbWifi = (CheckBox) view.findViewById(R.id.tv_wifi);
        cbWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbWifi.isChecked()){
                    wifi = "ada";
                } else {
                    wifi = "kosong";
                }
            }
        });
        cbRuangtamu = (CheckBox) view.findViewById(R.id.tv_rtamu);
        cbRuangtamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbRuangtamu.isChecked()){
                    ruang_tamu = "ada";
                } else {
                    ruang_tamu = "kosong";
                }
            }
        });
        cbDapur = (CheckBox) view.findViewById(R.id.tv_dapur);
        cbDapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDapur.isChecked()){
                    dapur = "ada";
                } else {
                    dapur = "kosong";
                }
            }
        });
        cbAc = (CheckBox) view.findViewById(R.id.tv_ac);
        cbAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAc.isChecked()){
                    ac = "ada";
                } else {
                    ac = "kosong";
                }
            }
        });
        cbTv = (CheckBox) view.findViewById(R.id.tv_tv);
        cbTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbTv.isChecked()){
                    tv = "ada";
                } else {
                    tv = "kosong";
                }
            }
        });
        cbParkir = (CheckBox) view.findViewById(R.id.tv_parkir);
        cbParkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbParkir.isChecked()){
                    parkir = "ada";
                } else {
                    parkir = "kosong";
                }
            }
        });
        cari = (Button) view.findViewById(R.id.btnSearch);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CariActivity.class);
                intent.putExtra("status", status);
                intent.putExtra("jenis", tvJenis.getText().toString());
                intent.putExtra("harga_min", tvAwal);
                intent.putExtra("harga_max", tvAkhir);
                intent.putExtra("bed", bed);
                intent.putExtra("almari", almari);
                intent.putExtra("km_dalam", kamar);
                intent.putExtra("meja_belajar", meja);
                intent.putExtra("wifi", wifi);
                intent.putExtra("ruang_tamu", ruang_tamu);
                intent.putExtra("dapur", dapur);
                intent.putExtra("ac", ac);
                intent.putExtra("tv", tv);
                intent.putExtra("parkir", parkir);
                startActivity(intent);
            }
        });
        return view;
    }

    private void AlertDialogStatus() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Ketersediaan Kamar");

        builder.setItems(sedia,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        tvAvailability.setText("Semua");
                        status = "kosong";
                        break;
                    case 1:
                        tvAvailability.setText("Kamar tersedia");
                        status = "1";
                        break;
                }
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
    }

    private void AlertDialogJenis() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Jenis Kost")
                .setItems(jenis,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                tvJenis.setText("Harian");
                                break;
                            case 1:
                                tvJenis.setText("Bulanan");
                                break;
                            case 2:
                                tvJenis.setText("Tahunan");
                                break;
                        }
                        alertDialog2.dismiss();
                    }
                });
        alertDialog2 = builder.create();
        alertDialog2.show();
    }
}
