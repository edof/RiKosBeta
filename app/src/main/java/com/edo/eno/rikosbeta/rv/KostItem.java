package com.edo.eno.rikosbeta.rv;

import java.io.Serializable;

/**
 * Created by Edo Firmansyah on 23/03/2017.
 */

public class KostItem implements Serializable {

    public int id_kost;

    public String nama, harga, luas, alamat, deskripsi,
            status, owner, telepon,latitude, longitude, foto;
}
