package com.example.myapplication.ViewModel;

import android.app.Activity;
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
import android.widget.TextView;

import com.example.myapplication.Model.baseAPI;
import com.example.myapplication.R;

import java.net.HttpURLConnection;
import java.net.URL;


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
    private String ua;
    /**************************** View元件變數 *********************************/
    private Activity activity;
    private WebView webview;
    private baseAPI baseAPI;
    private TextView tvID;
    private TextView tvTitle;
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
        if (bundle.getSerializable("base")==null){
            return;
        }
        baseAPI = (com.example.myapplication.Model.baseAPI) bundle.getSerializable("base");

        tvID = view.findViewById(R.id.tvID);
        tvTitle = view.findViewById(R.id.tvTitle);
        webview=view.findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            if (!settings.getLayoutAlgorithm().equals(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING))
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            if (!settings.getLoadWithOverviewMode()) settings.setLoadWithOverviewMode(true);
            if (!settings.getUseWideViewPort()) settings.setUseWideViewPort(true);
        }

        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setScrollbarFadingEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);


        ua = settings.getUserAgentString();
    }



    private void initData() {

    }

    private void setSystemServices() {
    }

    private void setLinstener() {

        try {
            URL myUrl = new URL(baseAPI.getThumbnailUrl());//參數是url
            HttpURLConnection myConn = (HttpURLConnection)myUrl.openConnection();//打開連接
            myConn.setRequestProperty( "User-agent" , ua );//為連接設置ua
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(String.valueOf(myUrl));
        }catch (Exception e){

        }
        tvID.setText(String.valueOf(baseAPI.getId()));
        tvTitle.setText(baseAPI.getTitle());

    }



}
