package com.edo.eno.rikosbeta.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.app.AppConfig;
import com.edo.eno.rikosbeta.app.AppController;
import com.edo.eno.rikosbeta.helper.SQLiteHandler;
import com.edo.eno.rikosbeta.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edo Firmansyah on 14/03/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button btLogin;
    private TextView btLinkRegister;
    private EditText etUname;
    private EditText etPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUname = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        btLogin = (Button) findViewById(R.id.btLogin);
        btLinkRegister = (TextView) findViewById(R.id.txRegister);

        //progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        //SQLite db handler
        db = new SQLiteHandler(getApplicationContext());
//        db.deleteUsers();

        //session manager
        session = new SessionManager(getApplicationContext());

        //cek login
        if (session.isLoggedIn()) {
            //user sudah login. arahkan ke main activity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        //login button click event
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = etUname.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                //cek data kosong
                if (!uname.isEmpty() && !password.isEmpty()) {
                    //login user
                    checkLogin(uname, password);
                } else {
                    //beritau user
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi data", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Link ke halaman reegister
        btLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    //fungsi untuk verifikasi login di mysql db
    private void checkLogin(final String uname, final String password) {
        //tag used to cancel req
        String tag_string_req = "req_login";

        pDialog.setMessage("Sedang masuk. . .");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Login Response: " + response.toString());
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");

                            //cek error node di json
                            if (!error) {
                                //sukses login, lalu bikin sesi login
                                session.setLogin(true);

                                //store user di SQLite
                                String uid = jObj.getString("uid");

                                JSONObject user = jObj.getJSONObject("user");
                                String fullname = user.getString("fullname");
                                String username = user.getString("username");
                                String email = user.getString("email");
                                String created_at = user.getString("created_at");

                                //insert row di tabel user
                                db.addUser(fullname, username, email, uid, created_at);

                                //launch main activity
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //error
                                String errorMsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            //JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "JSON error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                //posting parameter untuk url login
                Map<String, String> params = new HashMap<String, String>();
                params.put("uname", uname);
                params.put("password", password);

                return params;
            }
        };

        //add request for request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
