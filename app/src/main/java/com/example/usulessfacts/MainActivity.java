package com.example.usulessfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.usulessfacts.intarfaces.IMainActivity;
import com.example.usulessfacts.model.FactsModel;
import com.example.usulessfacts.presenter.MainActivityPresenter;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private MainActivityPresenter presenter;
    private TextView factBody;
    private Button getNewFactButton,getPreviousFact;
    private ImageButton shareButton;
    private ConstraintLayout layout;
    private String currentString,oldString;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        iniUi();
        clickListeners();
        onNetworkConnectionChecked();
        setAnimation();
    }

    public void iniUi(){
        factBody = findViewById(R.id.fact_id);
        getNewFactButton = findViewById(R.id.new_fact_button);
        layout = findViewById(R.id.parentFor_factBody);
        getPreviousFact = findViewById(R.id.previous_fact_button);
        shareButton = findViewById(R.id.share_button);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
    }

    public void clickListeners(){
        getNewFactButton.setOnClickListener(v -> {
            if (checkNetworkConnection()) {
                presenter.fetchFacts();
                setBackground();
            }else {
                Toast.makeText(this," Network connection not available ",Toast.LENGTH_LONG).show();
            }
        });

        getPreviousFact.setOnClickListener(v -> {
            factBody.setText(oldString);
            setAnimation();
        });

        shareButton.setOnClickListener(v ->{
            setShareIntent(currentString);
        });

        swipeRefreshLayout.setOnRefreshListener(this::onNetworkConnectionChecked);
    }

    @Override
    public void onUpdateFact(FactsModel factsModel) {
        setAnimation();
        factBody.setText(factsModel.getText());
        startOldFact(factsModel.getText());
    }

    @Override
    public void onUpdateTodayFact(FactsModel factsModel) {
        setAnimation();
        factBody.setText(factsModel.getText());
    }

    public boolean checkNetworkConnection(){
        boolean wifiConnected = false;
        boolean mobileDataConnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo() ;
        if (networkInfo != null && networkInfo.isConnected()){
            wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileDataConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return wifiConnected||mobileDataConnected;
    }

    public void onNetworkConnectionChecked (){
        if (checkNetworkConnection()){
            startTodayFact();
        } else if (!checkNetworkConnection()){
            Toast.makeText(this," Network connection not available ",Toast.LENGTH_LONG).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void startTodayFact(){
        startProgressDialog();
        presenter.fetchTodayFact();
    }

    public void startOldFact(String string) {
        oldString = currentString;
        currentString = string;
    }

    public void startProgressDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("Please Wait.. ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void endProgressDialog(){
        swipeRefreshLayout.setRefreshing(false);
        progressDialog.dismiss();
    }

    public void setBackground(){
        Resources res = getResources();
        @SuppressLint("Recycle") final TypedArray myImages = res.obtainTypedArray(R.array.background_images);
        final Random random = new Random();
        int randomInt = random.nextInt(myImages.length());
        int drawableID = myImages.getResourceId(randomInt, -1);
        layout.setBackgroundResource(drawableID);
    }

    public void setAnimation(){
        final Random random = new Random();
        int randomInt = random.nextInt(8);
        switch(randomInt) {
            case 0:
                YoYo.with(Techniques.Shake)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 1:
                YoYo.with(Techniques.Pulse)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 2:
                YoYo.with(Techniques.Flash)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 3:
                YoYo.with(Techniques.Wobble)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 4:
                YoYo.with(Techniques.RubberBand)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 5:
                YoYo.with(Techniques.DropOut)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 6:
                YoYo.with(Techniques.StandUp)
                        .duration(600)
                        .playOn(factBody);
                break;
            case 7:
                YoYo.with(Techniques.Wave)
                        .duration(600)
                        .playOn(factBody);
                break;
        }
    }

    public void setShareIntent(String shareIntentString){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareIntentString);
        startActivity(sharingIntent);
    }
}
