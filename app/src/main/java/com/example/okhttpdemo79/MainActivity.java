package com.example.okhttpdemo79;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.okhttpdemo79.adapter.RecyclerViewAdapter;
import com.example.okhttpdemo79.beans.GetResult;
import com.example.okhttpdemo79.beans.PostRequest;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        for (int i = 0; i < 10; i++) {
            Intent intent = new Intent(MainActivity.this,MyService.class);
            startService(intent);
        }
    }

    private void initView() {
        mTextView = findViewById(R.id.resultMess);
        RecyclerView recyclerView = findViewById(R.id.rv_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(mRecyclerViewAdapter);
    }

    public void getRequest(View view) {
        //先来okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .build();
        //请求
        Request request = new Request.Builder()
                .get()
                .url(Constant.get_url)
                .build();
        //返回数据的处理
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.code());
                //Log.e(TAG, "onResponse: " + response.body().string());
                String jsonstr = response.body().string();
                Gson gson = new Gson();
                final GetResult getResult = gson.fromJson(jsonstr, GetResult.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerViewAdapter.setData(getResult.getData());
                    }
                });

            }
        });

    }

    public void getRequestParam(View view) {
        //先来okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .build();

        Map<String, String> map = new HashMap<>();
        map.put("keyword", "123");
        map.put("page", "1");
        map.put("order", "1");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        //迭代器
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            stringBuilder.append(next.getKey());
            stringBuilder.append("=");
            stringBuilder.append(next.getValue());
            if (iterator.hasNext()) {
                stringBuilder.append("&");
            }
        }
        String str = stringBuilder.toString();
        //请求
        Request request = new Request.Builder()
                .get()
                .url(Constant.get_url_param + str)
                .build();
        //返回数据的处理
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.code());
                Log.e(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    public void postRequest(View view) {
        //先来okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.MILLISECONDS)
                .build();
        //bean
        PostRequest request = new PostRequest();
        request.setArticleId("123");
        request.setCommentContent("hello");
        //gson快速变为json,或者json变为对象
        Gson gson = new Gson();
        String jsonStr = gson.toJson(request);
        //类型
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody requestBody = RequestBody.create(jsonStr,mediaType);
        //请求
        Request request1 = new Request.Builder()
                .post(requestBody)
                .url(Constant.post_url)
                .build();
        //返回数据的处理
        okHttpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.code());
                Log.e(TAG, "onResponse: "+Thread.currentThread().getId() );
                //Log.e(TAG, "onResponse: " + response.body().string());
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: "+Thread.currentThread().getId() );
                        mTextView.setText(string);
                    }
                });
            }
        });
    }
}
