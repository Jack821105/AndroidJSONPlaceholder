package com.example.myapplication.ViewModel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Common.ImageTask;
import com.example.myapplication.Model.baseAPI;
import com.example.myapplication.R;

import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.http.POST;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThridFragment extends Fragment {

    /*************************************************************************
     * 常數數宣告區
     *************************************************************************/
    private final static String TAG = "ThridFragment";


    /*************************************************************************
     * 變數數宣告區
     *************************************************************************/
    private ImageTask ivImageTask;
    private Bitmap bitmap;

    /**************************** View元件變數 *********************************/
    private Activity activity;
    private baseAPI baseAPI;
    private TextView tvID;
    private TextView tvTitle;
    private ImageView ivUrl;

    /**************************** Adapter元件變數 ******************************/

    /**************************** Array元件變數 ********************************/

    /**************************** 資料集合變數 *********************************/

    /**************************** 物件變數 *************************************/

    /*************************************************************************
     * Override 函式宣告 (覆寫)
     *************************************************************************/

    public ThridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thrid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        /* 初始化資料,包含從其他Activity傳來的Bundle資料 ,Preference資枓 */
        initData();
        /* 設置必要的系統服務元件如: Services、BroadcastReceiver */
        setSystemServices();
        /* 設置View元件對應的linstener事件,讓UI可以與用戶產生互動 */
        setLinstener();
    }

    private void findViews(View view) {

        Bundle bundle = getArguments();
        if (bundle.getSerializable("base") == null) {
            return;
        }
        baseAPI = (com.example.myapplication.Model.baseAPI) bundle.getSerializable("base");

        tvID = view.findViewById(R.id.tvID);
        tvTitle = view.findViewById(R.id.tvTitle);
        ivUrl = view.findViewById(R.id.iv);
    }


    private void initData() {

    }

    private void setSystemServices() {
    }

    private void setLinstener() {


        bitmap = MainActivity.memoryCache.get(baseAPI.getId());

        if (bitmap!=null){
            ivUrl.setImageBitmap(bitmap);
            Log.e(TAG,"1");
        }else{

            Log.e(TAG,"2");
        }

        tvID.setText(String.valueOf(baseAPI.getId()));
        tvTitle.setText(baseAPI.getTitle());

    }


}
