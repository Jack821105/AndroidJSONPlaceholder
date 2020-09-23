package com.example.myapplication.ViewModel;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FristFragment extends Fragment {




    /*************************************************************************
     * 常數數宣告區
     *************************************************************************/

    /*************************************************************************
     * 變數數宣告區
     *************************************************************************/

    /**************************** View元件變數 *********************************/
    private Activity activity;
    private Button btnRequsetAPI;

    /**************************** Adapter元件變數 ******************************/

    /**************************** Array元件變數 ********************************/

    /**************************** 資料集合變數 *********************************/

    /**************************** 物件變數 *************************************/


    public FristFragment() {
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
        return inflater.inflate(R.layout.fragment_frist, container, false);
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

        btnRequsetAPI = view.findViewById(R.id.btnRequsetAPI);

    }

    private void initData() {
    }

    private void setSystemServices() {
    }

    private void setLinstener() {

        btnRequsetAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fristFragment_to_senondFragment);
            }
        });

    }
}
