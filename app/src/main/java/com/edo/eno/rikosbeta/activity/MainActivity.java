package com.edo.eno.rikosbeta.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.app.AppConfig;
import com.edo.eno.rikosbeta.app.VolleySingleton;
import com.edo.eno.rikosbeta.helper.SQLiteHandler;
import com.edo.eno.rikosbeta.helper.SessionManager;
import com.edo.eno.rikosbeta.rv.KostAdapter;
import com.edo.eno.rikosbeta.rv.KostItem;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView rvItem;
    ProgressBar progressBar;
    SessionManager session;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tampung view toolbar ke variabel mToolbar
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //menggunakan toolbar sebagai actionbar
        setSupportActionBar(mToolbar);

        //mengubah judul toolbar dengan string pada values string "app_name"
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        mToolbar.setTitle(getResources().getString(R.string.app_name));

//        mToolbar.setLogo(R.drawable.rikos_font);

        progressBar = (ProgressBar) findViewById(R.id.progressbar_kost);

        rvItem = (RecyclerView) findViewById(R.id.rvKost);
        rvItem.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvItem.setLayoutManager(manager);
        rvItem.setItemAnimator(new DefaultItemAnimator());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                AppConfig.URL_KOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //hide progressbar
                        progressBar.setVisibility(View.GONE);
                        //convert JSON data to arraylist then put to kost class
                        ArrayList<KostItem> kostItemList = new JsonConverter<KostItem>().toArrayList(response, KostItem.class);
                        //make new object adapter postingan
                        KostAdapter kostAdapter = new KostAdapter(MainActivity.this, kostItemList);
                        //inisialisasi recyclerview
//                        rvItem = (RecyclerView)findViewById(R.id.rvKost);
//                        //mengatur layoutmanager dan mengubah srcoll ke horizontal?
////                        rvItem.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                        LinearLayoutManager manager = new LinearLayoutManager(this);
//                        rvItem.setLayoutManager(manager);
//                        //mengatur tinggi atau lebar item recyclerview tetap/fix
//                        rvItem.setHasFixedSize(true);
//                        rvItem.setItemAnimator(new DefaultItemAnimator());
                        //set adapter ke recyclerview
                        rvItem.setAdapter(kostAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_profile:
//                Toast.makeText(this, "Menu Profile belum ada untuk saat ini", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.action_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Log Out Akun?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setCancelable(false);
                                progressDialog.setMessage("Logging out...");
                                progressDialog.setProgress(1000);
                                progressDialog.show();
                                logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "Tentang Aplikasi", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_exit:
                Toast.makeText(this, "Keluari", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logoutUser(){
        session.setLogin(false);
        db.deleteUsers();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}
