package com.example.okhttpdemo79;

import android.app.DownloadManager;

import java.io.IOException;

import okhttp3.Request;

/**
 * @author Tian
 * @description
 * @date :2020/7/24 19:15
 */
public abstract class ResultCallback {
    public abstract void onError(Request request , Exception e);
    public abstract void onRespose(String str) throws IOException;
}
