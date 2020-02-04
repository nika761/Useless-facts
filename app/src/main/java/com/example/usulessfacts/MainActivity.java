package com.example.usulessfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.usulessfacts.intarfaces.IMainActivity;
import com.example.usulessfacts.model.FactsModel;
import com.example.usulessfacts.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private MainActivityPresenter presenter;
    private TextView factBody;
    private Button getFactButton,previousFact;
    private ConstraintLayout layout;
    private String newString,oldString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        iniUi();
        addListeners();
        presenter.fetchTodayFact();
        setAnimation();
    }

    public void iniUi(){
        factBody = findViewById(R.id.fact_id);
        getFactButton = findViewById(R.id.get_button);
        layout = findViewById(R.id.main_layout);
        previousFact = findViewById(R.id.previous_button);
    }

    public void addListeners(){
        getFactButton.setOnClickListener(v -> {
            presenter.fetchFacts();
            setBackground();
        });
        previousFact.setOnClickListener(v -> {
            factBody.setText(oldString);
        });
    }

    @Override
    public void onUpdateFact(FactsModel factsModel) {
        factBody.setText(factsModel.getText());
    }

    @Override
    public void onUpdateTodayFact(FactsModel factsModel) {
        factBody.setText(factsModel.getText());
    }

    @Override
    public void getOldFact(String string) {
        oldString = newString;
        newString = string;
    }

    public void setBackground(){
        Resources res = getResources();
        final TypedArray myImages = res.obtainTypedArray(R.array.background_images);
        final Random random = new Random();
        int randomInt = random.nextInt(myImages.length());
        int drawableID = myImages.getResourceId(randomInt, -1);
        layout.setBackgroundResource(drawableID);
    }

    public void setAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation_for_text);
        factBody.setAnimation(animation);
    }
}
