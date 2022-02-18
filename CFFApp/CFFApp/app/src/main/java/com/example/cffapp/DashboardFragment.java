package com.example.cffapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment implements UpdateRecyclerView {

    private static final int REQUEST_CODE_SPEED_INPUT = 1000;
    private RecyclerView recyclerView,recyclerView2;
    private StaticRvAdapter staticRvAdapter;
    ImageView profie, notify;
    FloatingActionButton giohang;
    ImageView search, voice;
    EditText search_bar;

    ArrayList<DymanicRVModel> items = new ArrayList();
    DymanicRVAdapter dymanicRVAdapter;
    UpdateSelectedItems updateSelectedItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        final ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add( new StaticRvModel(R.drawable.chicken_icon,"CHICKEN"));
        item.add( new StaticRvModel(R.drawable.buger,"BURGER"));
        item.add( new StaticRvModel(R.drawable.milktea,"MILK TEA"));
        item.add( new StaticRvModel(R.drawable.combo,"COMBO"));
        item.add(new StaticRvModel(R.drawable.fries,"FAST FOOD"));


        recyclerView = root.findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item,getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        items = new ArrayList<>();

        recyclerView2 = root.findViewById(R.id.rv_2);
        dymanicRVAdapter = new DymanicRVAdapter(items,getActivity(),updateSelectedItems);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView2.setAdapter(dymanicRVAdapter);

        // sự kiện khác
        profie = root.findViewById(R.id.profile);
        giohang = root.findViewById(R.id.fab_basket);
        notify = root.findViewById(R.id.notify);
        search_bar = root.findViewById(R.id.search_bar);
        search = root.findViewById(R.id.search);

        profie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(ProfileNguoiDung.email == null){
                    intent = new Intent(getActivity(), Login.class);
                }else {
                    intent = new Intent(getActivity(), Profile.class);
                }
                startActivity(intent);

            }
        });
        voice = root.findViewById(R.id.voice);
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Basket.class);
                startActivity(intent);
            }
        });

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Notification.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ArrayList<DymanicRVModel> items = SelecteMonAn.search(search_bar.getText().toString());
                dymanicRVAdapter = new DymanicRVAdapter(items,getActivity(),updateSelectedItems);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                recyclerView2.setAdapter(dymanicRVAdapter);
            }
        });
        return root;
    }

    @Override
    public void callback(int position, ArrayList<DymanicRVModel> items) {
        dymanicRVAdapter = new DymanicRVAdapter(items,getActivity(),updateSelectedItems);
        dymanicRVAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dymanicRVAdapter);
    }


    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private void speak(){
        Intent  intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Tìm kiếm món ăn của bạn");
        try{

            startActivityForResult(intent,REQUEST_CODE_SPEED_INPUT);
        }catch (Exception e){
            Toast.makeText(getContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEED_INPUT:{
                if(resultCode == RESULT_OK && null!= data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    search_bar.setText(result.get(0));
                }
                break;

            }
        }
    }
}