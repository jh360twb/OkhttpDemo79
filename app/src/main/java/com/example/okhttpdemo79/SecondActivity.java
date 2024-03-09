package com.example.okhttpdemo79;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.okhttpdemo79.fragments.FirstFragment;
import com.example.okhttpdemo79.fragments.SecondFragment;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_avtivity);
        initView();
    }

    private void initView() {
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_first,firstFragment,"firstFragment").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_second,secondFragment,"secondFragment").commit();

    }
}
