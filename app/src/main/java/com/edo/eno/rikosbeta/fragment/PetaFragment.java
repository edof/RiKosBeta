package com.edo.eno.rikosbeta.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.edo.eno.rikosbeta.R;
import com.edo.eno.rikosbeta.app.AppConfig;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edo Firmansyah on 01/05/2017.
 */

public class PetaFragment extends Fragment implements OnMapReadyCallback {

    public GoogleMap mMap;
    public PetaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peta, container, false);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        transaction.replace(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);
        String uri = AppConfig.URL_KOST;

        RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest request = new JsonArrayRequest(
                uri,
                new Response.Listener<JSONArray>() {
                    LatLng location;

                    @Override
                    public void onResponse(JSONArray response) {
                        int count = response.length();
                        for (int i = 0; i < count; i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Double lat = Double.parseDouble(jsonObject.getString("latitude"));
                                Double lng = Double.parseDouble(jsonObject.getString("longitude"));

                                location = new LatLng(lat, lng);
                                MarkerOptions options = new MarkerOptions();
                                options.position(location);
                                options.title(jsonObject.getString("nama"));
                                options.snippet(jsonObject.getString("alamat"));
                                mMap.addMarker(options);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        CameraUpdate cu = CameraUpdateFactory.newLatLng(location);
                        mMap.moveCamera(cu);
                        onMapReady(mMap);
//                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "onErrorResponse", Toast.LENGTH_LONG).show();
                    }
                }
        );
        Object TAG_REQUEST_QUEUE = new Object();
        request.setTag(TAG_REQUEST_QUEUE);
        mRequestQueue.add(request);
        mRequestQueue.start();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng tembalang = new LatLng(-7.055969, 110.439235);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tembalang,14));
    }
}