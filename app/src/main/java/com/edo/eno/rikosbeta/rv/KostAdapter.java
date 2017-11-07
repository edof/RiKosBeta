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
                .load(kostItem.getFoto())
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

        kostHolder.tvNama.setText(kostItem.getNama());
        kostHolder.tvHarga.setText("Rp " + kostItem.getHarga()+ ",-");
        kostHolder.tvAlamat.setText(kostItem.getAlamat());
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

                    String getNama = ki.getNama();
                    iKost.putExtra("namaKost", getNama);
                    Log.e("passing nama: ", ki.getNama());

                    String getHarga = ki.getHarga();
                    iKost.putExtra("hargaKost", getHarga);
                    Log.e("passing harga: ", ki.getHarga());

                    String getLuas = ki.getLuas();
                    iKost.putExtra("luasKost", getLuas);
                    Log.e("passing luas: ", ki.getLuas());

                    String getAlamat = ki.getAlamat();
                    iKost.putExtra("alamatKost", getAlamat);
                    Log.e("passing alamat: ", ki.getAlamat());

                    String getDeskripsi = ki.getDeskripsi();
                    iKost.putExtra("deskripsiKost", getDeskripsi);
                    Log.e("passing deskrip: ", ki.getStatus());

                    String getStatus = ki.getStatus();
                    iKost.putExtra("statusKost", getStatus);
                    Log.e("passing status: ", ki.getDeskripsi());

                    String getOwner = ki.getOwner();
                    iKost.putExtra("ownerKost", getOwner);
                    Log.e("passing owner: ", ki.getOwner());

                    String getTelepon = ki.getTelepon();
                    iKost.putExtra("teleponKost", getTelepon);
                    Log.e("passing telepon: ", ki.getTelepon());

                    String getLatitude = ki.getLatitude();
                    iKost.putExtra("latitudeKost", getLatitude);
                    Log.e("passing lat: ", ki.getLatitude());

                    String getLongitude = ki.getLongitude();
                    iKost.putExtra("longitudeKost", getLongitude);
                    Log.e("passing long: ", ki.getLongitude());

                    String getFoto = ki.getFoto();
                    iKost.putExtra("fotoKost", getFoto);
                    Log.e("passing foto: ", ki.getFoto());

                    String getBed = ki.getBed();
                    iKost.putExtra("bedKost", getBed);

                    String getKm = ki.getKm_dalam();
                    iKost.putExtra("kmKost", getKm);

                    String getAlmari = ki.getAlmari();
                    iKost.putExtra("almariKost", getAlmari);

                    String getMeja = ki.getMeja_belajar();
                    iKost.putExtra("mejaKost", getMeja);

                    String getWifi = ki.getWifi();
                    iKost.putExtra("wifiKost", getWifi);

                    String getRuang = ki.getRuang_tamu();
                    iKost.putExtra("ruangKost", getRuang);

                    String getDapur = ki.getDapur();
                    iKost.putExtra("dapurKost", getDapur);

                    String getKulkas = ki.getKulkas();
                    iKost.putExtra("kulkasKost", getKulkas);

                    String getTv = ki.getTv();
                    iKost.putExtra("tvKost", getTv);
                    Log.e("passing fasilitas tv", ki.getTv());

                    String getParkir = ki.getParkir_mobil();
                    iKost.putExtra("parkirKost", getParkir);

                    v.getContext().startActivity(iKost);
                }
            });
        }
    }
}
