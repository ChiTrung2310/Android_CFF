package com.example.cffapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ProfileProcessOrder  extends Fragment implements  View.OnClickListener{
    Button xacNhan;
    EditText hoTen, soDt,ghiChu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_order_fragment,container,false);
        xacNhan = root.findViewById(R.id.buttom_XacNhanPro);
        xacNhan.setOnClickListener(this);
        hoTen = root.findViewById(R.id.hoten_profile_order);
        soDt = root.findViewById(R.id.mobile_order);
        ghiChu = root.findViewById(R.id.ghiChu);
        return root;
    }


    @Override
    public void onClick(View v) {
        OrderThongTIn.Ten = hoTen.getText().toString();
        OrderThongTIn.soDienThoai = soDt.getText().toString();
        OrderThongTIn.ghiChu = ghiChu.getText().toString();
        Navigation.findNavController(v).navigate(R.id.action_profileProcessOrder_to_diaChiProcessOrder2);
    }
}
