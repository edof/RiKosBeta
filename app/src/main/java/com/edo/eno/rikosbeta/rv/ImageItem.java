package com.edo.eno.rikosbeta.rv;

import java.io.Serializable;

/**
 * Created by Edo Firmansyah on 13/11/2017.
 */

public class ImageItem implements Serializable{

    private String nama_gambar;

    public String getNama_gambar() {
        return nama_gambar;
    }

    public void setNama_gambar(String nama_gambar) {
        this.nama_gambar = nama_gambar;
    }

    public ImageItem(String nama_gambar) {

        this.nama_gambar = nama_gambar;
    }
}
