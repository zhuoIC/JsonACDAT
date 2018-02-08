package com.example.usuario.jsonacdat.network;

import com.example.usuario.jsonacdat.ui.retrofit.Repo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by usuario on 8/02/18.
 */

public interface ApiService {
    /**
     * Llamada teniendo en cuenta la base url creada en apiadapter
     * @param username
     * @return
     */
    @GET("/users/{username}/repos")
    Call<ArrayList<Repo>> reposForUser(@Path("username") String username);

}
