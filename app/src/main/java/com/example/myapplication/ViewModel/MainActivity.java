package com.example.myapplication.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.cache.MemoryCache;

public class MainActivity extends AppCompatActivity {


    public static MemoryCache memoryCache = new MemoryCache();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
