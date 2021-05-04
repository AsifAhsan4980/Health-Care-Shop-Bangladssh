package com.healthcareshopbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoInternetActivity extends AppCompatActivity {

    SwipeRefreshLayout mySwipeRefreshLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.refresh);
        button=(Button)findViewById(R.id.refresh_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNetworkAvailable();
            }
        });


        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        isNetworkAvailable();
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

    }

    private void isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            activeNetworkInfo.isConnected();
            Intent mainIntent = new Intent(NoInternetActivity.this,MainActivity.class);
            NoInternetActivity.this.startActivity(mainIntent);
            NoInternetActivity.this.finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_SHORT).show();
        }

    }
}