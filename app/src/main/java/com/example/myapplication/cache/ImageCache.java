package com.example.myapplication.cache;

import android.graphics.Bitmap;

public interface ImageCache {
    Bitmap get(int isbn);
    void put(int isbn, Bitmap bitmap);
    void ifExistDelete(int isbn);
}
