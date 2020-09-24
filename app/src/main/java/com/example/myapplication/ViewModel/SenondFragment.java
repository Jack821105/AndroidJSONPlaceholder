package com.example.myapplication.ViewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Common.GetDataService;
import com.example.myapplication.Common.RetrofitClientInstance;
import com.example.myapplication.Model.baseAPI;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SenondFragment extends Fragment {


    /*************************************************************************
     * 常數數宣告區
     *************************************************************************/
    private static final String TAG = "SecondFragment";

    /*************************************************************************
     * 變數數宣告區
     *************************************************************************/

    private List<baseAPI> baseList;
    private String ua;


    /**************************** View元件變數 *********************************/
    private Activity activity;
    private RecyclerView rvBase;

    /**************************** Adapter元件變數 ******************************/

    /**************************** Array元件變數 ********************************/

    /**************************** 資料集合變數 *********************************/


    /**************************** 物件變數 *************************************/

    public SenondFragment() {
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
        return inflater.inflate(R.layout.fragment_senond, container, false);
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

        rvBase = view.findViewById(R.id.rvBase);
    }

    private void initData() {
        baseList = getData();
    }

    private List<baseAPI> getData() {

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        final List<baseAPI> bases = null;
        Call<List<baseAPI>> call = service.getAllPosts();
        call.enqueue(new Callback<List<baseAPI>>() {
            @Override
            public void onResponse(Call<List<baseAPI>> call, Response<List<baseAPI>> response) {

                generateDataList(response.body());


            }

            @Override
            public void onFailure(Call<List<baseAPI>> call, Throwable t) {
                Toast.makeText(activity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        return bases;
    }


    private void generateDataList(List<baseAPI> baseList) {

        rvBase.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvBase.setAdapter(new rvBaseAdapter(activity, baseList));

    }

    private void setSystemServices() {
    }

    private void setLinstener() {

    }


    /*Adapter--------------------------------------------------------------------------------------------*/
    private class rvBaseAdapter extends RecyclerView.Adapter<rvBaseAdapter.Myviewholder> {
        Context context;
        List<baseAPI> baseList;

        public rvBaseAdapter(Context context, List<baseAPI> baseList) {

            this.context = context;
            this.baseList = baseList;
        }

        @Override
        public int getItemCount() {
            Log.e(TAG, "數量=" + baseList.size());
            return baseList != null ? baseList.size() : 0;
        }

        @NonNull
        @Override
        public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);


            return new Myviewholder(view);
        }


        private class Myviewholder extends RecyclerView.ViewHolder {
            WebView ivUrl;
            TextView tvID;
            TextView tvTitle;

            public Myviewholder(View view) {
                super(view);

                ivUrl = view.findViewById(R.id.ivUrl);
                tvID = view.findViewById(R.id.tvID);
                tvTitle = view.findViewById(R.id.tvTitle);
                WebSettings settings = ivUrl.getSettings();
                ivUrl.getSettings().setUseWideViewPort(true);
                ivUrl.getSettings().setLoadWithOverviewMode(true);
                ua = settings.getUserAgentString();
            }
        }


        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

            final baseAPI baseAPIFinal = baseList.get(position);


            holder.ivUrl.loadUrl(baseAPIFinal.getThumbnailUrl());
            holder.ivUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"123123123123");
                }
            });

            holder.ivUrl.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            break;

                        case MotionEvent.ACTION_UP:
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("base", baseAPIFinal);
                            Navigation.findNavController(v).navigate(R.id.action_senondFragment_to_thridFragment, bundle);
                            break;
                    }


                    return false;
                }
            });





            holder.tvID.setText(String.valueOf(baseAPIFinal.getId()));
            holder.tvTitle.setText(String.valueOf(baseAPIFinal.getTitle()));


        }


    }
    /*Adapter--------------------------------------------------------------------------------------------*/
}
