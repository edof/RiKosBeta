package com.edo.eno.rikosbeta.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.activity.LoginActivity;
import com.edo.eno.rikosbeta.helper.SQLiteHandler;
import com.edo.eno.rikosbeta.helper.SessionManager;

import java.util.HashMap;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class ProfilFragment extends Fragment {

    private TextView txtNama, txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        String name = getArguments().getString("name");
        String email = getArguments().getString("email");

        txtNama = (TextView) view.findViewById(R.id.namaPanjang);
        txtEmail = (TextView) view.findViewById(R.id.email);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        db = new SQLiteHandler(getActivity());
        session = new SessionManager(getActivity());

        if (session.isLoggedIn()){

            txtNama.setText(name);
            txtEmail.setText(email);
        } else {
            logoutUser();

        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        return view;

    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
