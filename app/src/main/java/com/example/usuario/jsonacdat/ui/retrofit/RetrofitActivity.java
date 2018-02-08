package com.example.usuario.jsonacdat.ui.retrofit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.usuario.jsonacdat.R;
import com.example.usuario.jsonacdat.network.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener, Callback<ArrayList<Repo>> {

    private EditText edtUser;
    private Button btnRepo;
    private RecyclerView rvRepos;
    private ReposAdapter adapter;
    private ArrayList<Repo> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        edtUser = findViewById(R.id.edtUser);
        btnRepo = findViewById(R.id.btnRepo);
        btnRepo.setOnClickListener(this);
        rvRepos = findViewById(R.id.rvRepo);
        adapter = new ReposAdapter();
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));

        rvRepos.addOnItemTouchListener(new RecyclerTouchListener(this,rvRepos, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Uri uri = Uri.parse(repos.get(position).getHtmlUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    Toast.makeText(getApplicationContext(), "No hay un navegador",
                            Toast.LENGTH_SHORT).show();
                Toast.makeText(RetrofitActivity.this, "Single Click on position: "+position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(RetrofitActivity.this, "Long press on position: "+position,
                        Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public void onClick(View view) {
        if(view == btnRepo){
            String username = edtUser.getText().toString();
            if(username.isEmpty()){
                showMessage("Debe dar un nombre");
            }else {
                //Call<ArrayList<Repo>> call = apiService.reposForUser(username);
                Call<ArrayList<Repo>> call = ApiAdapter.getInstance().reposForUser(username);;
                call.enqueue(this);
            }
        }
    }

    /**
     * Para mostrar errores hara un Toast y aparte mostrara el error en pantalla
     *
     * @param message
     */
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<ArrayList<Repo>> call, Response<ArrayList<Repo>> response) {
        // int statusCode = response.code();
        // User user = response.body();
        if(response.isSuccessful()){
            repos = response.body();
            adapter.setRepos(repos);
            showMessage("Repositorios actualizados ok");
        }else {
            StringBuilder message = new StringBuilder();
            message.append("Error en la descarga: " + response.code());
            if(response != null){
                message.append(response.body());
            }
            if(response.errorBody() != null){
                message.append(response.errorBody());
            }
            showMessage(message.toString());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Repo>> call, Throwable t) {
        if(t != null){
            showMessage("Fallo en la comunicación: " + t.getMessage());
        }
    }
}
