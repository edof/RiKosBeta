package com.edo.eno.rikosbeta.activity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.app.AppConfig;
import com.edo.eno.rikosbeta.app.AppController;
import com.edo.eno.rikosbeta.fragment.SlideshowDialogFragment;
import com.edo.eno.rikosbeta.rv.GalleryAdapter;
import com.edo.eno.rikosbeta.rv.ImageItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GalleryActivity extends AppCompatActivity {

    private String TAG = GalleryActivity.class.getSimpleName();
    private static final String endpoint = "http://rikos.cf/android/getFoto.php";
    private ArrayList<ImageItem> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private String foto, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        foto = getIntent().getStringExtra("id_kost");
        Log.e("get intent fab id kost", foto);
        nama = getIntent().getStringExtra("nama_kost");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle(nama);
        }

        Log.e("nama di gallery", nama);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

         recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        fetchImages(foto);
    }

    private void fetchImages(final String foto) {

//        pDialog.setMessage("Downloading json...");
//        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GET_FOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response.toString());
//                pDialog.hide();

                images.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        String namaGambar = data.getString("nama_gambar");
                        ImageItem image = new ImageItem(namaGambar);
                        images.add(image);
                    }
                    Log.d("image", String.valueOf(jsonArray));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "JSON gagal dimuat", Toast.LENGTH_LONG);
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Strong Req Error", Toast.LENGTH_LONG);
//                pDialog.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kost", foto);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq);
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
