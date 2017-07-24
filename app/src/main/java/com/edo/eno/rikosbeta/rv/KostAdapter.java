package com.edo.eno.rikosbeta.rv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.activity.DetailKostActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Edo Firmansyah on 23/03/2017.
 */

public class KostAdapter extends RecyclerView.Adapter<KostAdapter.KostViewHolder> {

    private Context mContext;
    private ArrayList<KostItem> mKostList;
    private ProgressBar pBar;

    public KostAdapter(Context context, ArrayList<KostItem> kostList) {
        this.mContext = context;
        this.mKostList = kostList;
    }

    @Override
    public KostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.kost_cardview, parent, false);
        KostViewHolder kostVH = new KostViewHolder(view);
        return kostVH;
    }

    @Override
    public void onBindViewHolder(KostViewHolder holder, int position) {
        KostItem kostItem = mKostList.get(position);
        KostViewHolder kostHolder = (KostViewHolder) holder;

        Picasso.with(mContext)
                .load(kostItem.foto)
//                .load("https://i1.sndcdn.com/artworks-000088295837-5nrb84-t500x500.jpg")
                .placeholder(R.drawable.rikos_icon)
                .error(R.drawable.ic_error)
                .fit()
                .centerCrop()
                .into(kostHolder.ivFoto);

//        Glide.with(mContext)
////                .load(kostItem.foto)
//                .load("https://i1.sndcdn.com/artworks-000088295837-5nrb84-t500x500.jpg")
//                .placeholder(R.drawable.rikos_icon)
//                .error(R.drawable.ic_error)
//                .crossFade()
////                .fit()
//                .into(kostHolder.ivFoto);

        kostHolder.tvNama.setText(kostItem.nama);
        kostHolder.tvHarga.setText(kostItem.harga);
        kostHolder.tvAlamat.setText(kostItem.alamat);
//        kostHolder.tvLuas.setText(kostItem.luas);
//        kostHolder.tvDeskripsi.setText(kostItem.deskripsi);
//        kostHolder.tvStatus.setText(kostItem.status);
//        kostHolder.tvOwner.setText(kostItem.owner);
//        kostHolder.tvTelepon.setText(kostItem.telepon);
//        kostHolder.tvLatitude.setText(kostItem.latitude);
//        kostHolder.tvLongitude.setText(kostItem.longitude);
    }

    @Override
    public int getItemCount() {

        if (mKostList !=null){
            return mKostList.size();
        }
        return 0;
    }

    //ViewHolder class
    public class KostViewHolder extends RecyclerView.ViewHolder{
        CardView cvKost;
        TextView tvNama, tvHarga, tvLuas, tvAlamat, tvDeskripsi,
                tvStatus, tvOwner, tvTelepon,tvLatitude, tvLongitude;
        ImageView ivFoto;

        KostViewHolder(View itemView) {
            super(itemView);
            cvKost = (CardView) itemView.findViewById(R.id.cvKost);
            tvNama = (TextView) itemView.findViewById(R.id.tvNama);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);
            ivFoto = (ImageView) itemView.findViewById(R.id.ivFoto);
//            tvLuas = (TextView) itemView.findViewById(R.id.detailLuas);
//            tvDeskripsi = (TextView) itemView.findViewById(R.id.detailDeskripsi);
//            tvStatus = (TextView) itemView.findViewById(R.id.detailStatus);
//            tvOwner = (TextView) itemView.findViewById(R.id.detailOwner);
//            tvTelepon = (TextView) itemView.findViewById(R.id.detailTelepon);

            cvKost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    KostItem ki = mKostList.get(position);
                    Intent iKost = new Intent(v.getContext(), DetailKostActivity.class);

                    Log.e("passing nama: ", ki.nama);
                    String getNama = ki.nama;
                    iKost.putExtra("namaKost", getNama);

                    String getHarga = ki.harga;
                    iKost.putExtra("hargaKost", getHarga);

                    String getLuas = ki.luas;
                    iKost.putExtra("luasKost", getLuas);

                    String getAlamat = ki.alamat;
                    iKost.putExtra("alamatKost", getAlamat);

                    String getDeskripsi = ki.deskripsi;
                    iKost.putExtra("deskripsiKost", getDeskripsi);

                    String getStatus = ki.status;
                    iKost.putExtra("statusKost", getStatus);

                    Log.e("passing nama: ", ki.owner);
                    String getOwner = ki.owner;
                    iKost.putExtra("ownerKost", getOwner);

                    String getTelepon = ki.telepon;
                    iKost.putExtra("teleponKost", getTelepon);

                    String getLatitude = ki.latitude;
                    iKost.putExtra("latitudeKost", getLatitude);

                    String getLongitude = ki.longitude;
                    iKost.putExtra("longitudeKost", getLongitude);

                    String getFoto = ki.foto;
                    iKost.putExtra("fotoKost", getFoto);

                    v.getContext().startActivity(iKost);
                }
            });
        }
    }
}
