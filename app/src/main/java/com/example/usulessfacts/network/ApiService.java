package com.example.usulessfacts.network;

import com.example.usulessfacts.model.FactsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET ("random.json?language=en")
    Call <FactsModel> fetchFacts();

    @GET("today.json?language=en")
    Call<FactsModel> fetchTodayFact();
}
