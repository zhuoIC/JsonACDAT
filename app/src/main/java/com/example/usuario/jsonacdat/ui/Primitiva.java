package com.example.usuario.jsonacdat.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.usuario.jsonacdat.R;
import com.example.usuario.jsonacdat.network.MySingleton;
import com.example.usuario.jsonacdat.utils.Analisis;

import org.json.JSONException;
import org.json.JSONObject;

public class Primitiva extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "MyTag";
    public static final String WEB = "http://192.168.3.57/acceso/primitiva.json";
    //public static final String WEB = "https://portadaalta.mobi/acceso/primitiva.json";
    Button mButton;
    TextView mTextView;
    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_primitiva );
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mTextView = findViewById(R.id.textView);
        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
    }
    @Override
    public void onClick(View view) {
        if (view == mButton) {
            descarga();
        }
    }
    private void descarga() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, WEB, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("onResponse", "Entra");
                            mTextView.setText(Analisis.analizarPrimitiva(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("onErrorResponse", "Entra");
                        mTextView.setText(error.getMessage());
                    }
                });

        // Set the tag on the request.
        jsObjRequest.setTag( TAG );
        Log.d("jsObjTAG", "Pasa");
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll( TAG );
        }
    }
}
