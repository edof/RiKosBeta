package com.edo.eno.rikosbeta.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.fragment.CariFragment;
import com.edo.eno.rikosbeta.fragment.KostFragment;
import com.edo.eno.rikosbeta.fragment.PetaFragment;
import com.edo.eno.rikosbeta.fragment.ProfilFragment;
import com.edo.eno.rikosbeta.helper.SQLiteHandler;
import com.edo.eno.rikosbeta.helper.SessionManager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    BottomBar mBottomBar;
    private CoordinatorLayout mCoordinatorLayout;
    private SQLiteHandler db;
    private SessionManager session;
    private Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_home);
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noNavBarGoodness();
        mBottomBar.setItems(R.menu.menu_bar);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                if (menuItemId == R.id.menu_home)
                {
                    KostFragment kostFragment = new KostFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, kostFragment).commit();
                }
                else if (menuItemId == R.id.menu_map)
                {
                    PetaFragment petaFragment = new PetaFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, petaFragment).commit();
                }
                else if (menuItemId == R.id.menu_cari)
                {
                    CariFragment cariFragment = new CariFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, cariFragment).commit();
                }
                else if (menuItemId == R.id.menu_profil)
                {
                    ProfilFragment profilFragment = new ProfilFragment();
                    profilFragment.setArguments(args);
                    getFragmentManager().beginTransaction().replace(R.id.frame, profilFragment).commit();
                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        mBottomBar.mapColorForTab(0, "#2962ff");
        mBottomBar.mapColorForTab(1, "#009688");
        mBottomBar.mapColorForTab(2, "#E91E63");
        mBottomBar.mapColorForTab(3, "#9C27B0");
        mBottomBar.attachShy(mCoordinatorLayout, savedInstanceState);

        db = new SQLiteHandler(this);
        session = new SessionManager(this);

        if (session.isLoggedIn()){
            HashMap<String, String> user = db.getUserDetail();

            String fullname = user.get("fullname");
            String email = user.get("email");

            args = new Bundle();
            args.putString("name", fullname);
            args.putString("email", email);
            Log.d(TAG, "session masih kesimpen");
        } else {
            logoutUser();
            Log.d(TAG, "user kehapus bos");
        }

    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mBottomBar.onSaveInstanceState(outState);
    }
}
