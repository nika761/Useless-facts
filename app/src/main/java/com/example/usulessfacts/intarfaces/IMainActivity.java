package com.example.usulessfacts.intarfaces;

import com.example.usulessfacts.model.FactsModel;

public interface IMainActivity {
    void onUpdateFact(FactsModel factsModel);
    void onUpdateTodayFact(FactsModel factsModel);
    void getOldFact(String string);
}
