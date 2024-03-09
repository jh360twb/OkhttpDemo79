package com.example.okhttpdemo79;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author Tian
 * @description
 * @date :2020/7/25 19:17
 */
public class MyService extends IntentService {
    private static final String TAG = MyService.class.getSimpleName();
    private int count = 0;

    public MyService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        count ++;
        Log.e(TAG, "onHandleIntent: "+count );
    }
}
