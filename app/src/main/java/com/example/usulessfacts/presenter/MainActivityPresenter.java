package com.example.usulessfacts.presenter;

import com.example.usulessfacts.intarfaces.IMainActivity;
import com.example.usulessfacts.model.FactsModel;
import com.example.usulessfacts.network.ApiService;
import com.example.usulessfacts.network.RetrofitManager;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {
    private IMainActivity iMainActivity;

    public MainActivityPresenter(IMainActivity iMainActivity){
        this.iMainActivity = iMainActivity;
    }

    public void fetchFacts(){
        ApiService service = RetrofitManager.getApiservice();
        service.fetchFacts().enqueue(new Callback<FactsModel>() {
            @Override
            public void onResponse(Call<FactsModel> call, Response<FactsModel> response) {
                if (Objects.requireNonNull(response).isSuccessful()){
                    FactsModel factsResponse = response.body();
                    iMainActivity.onUpdateFact(factsResponse);
                    assert response.body() != null;
                    getOldFact(response.body().getText());
                }
            }

            @Override
            public void onFailure(Call<FactsModel> call, Throwable t) {

            }
        });
    }

    public void getOldFact(String string){
        String str = string;
        iMainActivity.getOldFact(str);
    }

    public void fetchTodayFact(){
        ApiService service = RetrofitManager.getApiservice();
        service.fetchTodayFact().enqueue(new Callback<FactsModel>() {
            @Override
            public void onResponse(Call<FactsModel> call, Response<FactsModel> response) {
                if (Objects.requireNonNull(response.isSuccessful())){
                    FactsModel factsModel = response.body();
                    iMainActivity.onUpdateTodayFact(factsModel);
                }
            }

            @Override
            public void onFailure(Call<FactsModel> call, Throwable t) {

            }
        });
    }
}
