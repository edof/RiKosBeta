package com.edo.eno.rikosbeta.rv;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edo Firmansyah on 23/03/2017.
 */

public class KostItem implements Serializable {

    private String id_kost;
    private String nama;
    private String harga;
    private String luas;
    private String alamat;
    private String deskripsi;
    private String status;
    private String jenis;
    private String tipe;
    private String owner;
    private String telepon;
    private String latitude;
    private String longitude;
    private String nama_gambar;
    private String bed;
    private String km_dalam;
    private String almari;
    private String meja_belajar;
    private String wifi;
    private String ruang_tamu;
    private String dapur;
    private String ac;
    private String tv;
    private String parkir_mobil;

    public KostItem(String id_kost, String nama, String harga, String luas, String alamat, String deskripsi, String status, String jenis, String tipe, String owner, String telepon, String latitude, String longitude, String nama_gambar, String bed, String km_dalam, String almari, String meja_belajar, String wifi, String ruang_tamu, String dapur, String ac, String tv, String parkir_mobil) {
        this.id_kost = id_kost;
        this.nama = nama;
        this.harga = harga;
        this.luas = luas;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.status = status;
        this.jenis = jenis;
        this.tipe = tipe;
        this.owner = owner;
        this.telepon = telepon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nama_gambar = nama_gambar;
        this.bed = bed;
        this.km_dalam = km_dalam;
        this.almari = almari;
        this.meja_belajar = meja_belajar;
        this.wifi = wifi;
        this.ruang_tamu = ruang_tamu;
        this.dapur = dapur;
        this.ac = ac;
        this.tv = tv;
        this.parkir_mobil = parkir_mobil;
    }

    public String getId_kost() {
        return id_kost;
    }

    public void setId_kost(String id_kost) {
        this.id_kost = id_kost;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNama_gambar() {
        return nama_gambar;
    }

    public void setNama_gambar(String nama_gambar) {
        this.nama_gambar = nama_gambar;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getKm_dalam() {
        return km_dalam;
    }

    public void setKm_dalam(String km_dalam) {
        this.km_dalam = km_dalam;
    }

    public String getAlmari() {
        return almari;
    }

    public void setAlmari(String almari) {
        this.almari = almari;
    }

    public String getMeja_belajar() {
        return meja_belajar;
    }

    public void setMeja_belajar(String meja_belajar) {
        this.meja_belajar = meja_belajar;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getRuang_tamu() {
        return ruang_tamu;
    }

    public void setRuang_tamu(String ruang_tamu) {
        this.ruang_tamu = ruang_tamu;
    }

    public String getDapur() {
        return dapur;
    }

    public void setDapur(String dapur) {
        this.dapur = dapur;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getParkir_mobil() {
        return parkir_mobil;
    }

    public void setParkir_mobil(String parkir_mobil) {
        this.parkir_mobil = parkir_mobil;
    }
}