package com.example.myapplication.Common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.cache.ImageCache;
import com.example.myapplication.cache.MemoryCache;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageTask extends AsyncTask<Object, Integer, Bitmap> {
    private final static String TAG = "ImageTask";
    private String url;
    private int id;
    private MemoryCache memoryCache;
    /* ImageTask的屬性strong參照到SpotListFragment內的imageView不好，
        會導致SpotListFragment進入背景時imageView被參照而無法被釋放，
        而且imageView會參照到Context，也會導致Activity無法被回收。
        改採weak參照就不會阻止imageView被回收 */
    private WeakReference<ImageView> imageViewWeakReference;



    // 取完圖片後使用傳入的ImageView顯示，適用於顯示多張圖片
    public ImageTask(String url ,ImageView imageView,MemoryCache memoryCache,int id) {
        this.url = url;
        this.id = id;
        this.imageViewWeakReference = new WeakReference<>(imageView);
        this.memoryCache = memoryCache;
    }



    @Override
    protected Bitmap doInBackground(Object... params) {

        Bitmap bitmap = memoryCache.get(id);

        if (bitmap != null){
            Log.e(TAG,"1");
            return bitmap;
        }
        Log.e(TAG,"2");

        return getRemoteImage(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewWeakReference.get();
        if (isCancelled() || imageView == null) {
            return;
        }
        if (bitmap != null) {
            Log.e(TAG,"3");
            memoryCache.put(id,bitmap);
            imageView.setImageBitmap(bitmap);
        } else {
            Log.e(TAG,"4");
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    private Bitmap getRemoteImage(String url) {
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true); // allow inputs
            connection.setDoOutput(false); // allow outputs
            connection.setUseCaches(false); // do not use a cached copy
            connection.setRequestProperty("User-Agent", "Chrome 74 on Windows 10");
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            Log.d(TAG, "response code: " + responseCode);
            if (responseCode == 200) {
                bitmap = BitmapFactory.decodeStream(
                        new BufferedInputStream(connection.getInputStream()));
            } else {
                Log.d(TAG, "response code: " + responseCode);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return bitmap;
    }
}