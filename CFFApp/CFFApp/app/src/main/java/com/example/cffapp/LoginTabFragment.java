package com.example.cffapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginTabFragment extends Fragment {

    EditText email, password;
    TextView forget;
    Button login;
    float v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragement,container,false);
        email = (EditText) root.findViewById(R.id.email);
        password = (EditText) root.findViewById(R.id.password);
        forget = root.findViewById(R.id.forget);
        login = root.findViewById(R.id.buttom_dangnhap);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection conn = SERVER.Connect();
                String taiKhoan = email.getText().toString();
                String matKhau = password.getText().toString();
                if (conn == null) {

                } else {
                    try {
                        ResultSet resultSet;
                        String query = "Select * from TAI_KHOAN_TV where EMAIL = '" + taiKhoan + "' and  MATKHAU='" + matKhau + "'";
                        Statement stmt = conn.createStatement();
                        resultSet = stmt.executeQuery(query);//thực thi lệnh, trả về số dòng thực hiện dc

                        if(resultSet.next()){
                            Intent intent = new Intent(v.getContext(), Profile.class);
                            startActivity(intent);
                            getActivity().finish();
                            Toast.makeText(v.getContext(), "Xin chào " +taiKhoan+"", Toast.LENGTH_SHORT).show();

                            ProfileNguoiDung.email = resultSet.getString("EMAIL");
                            ProfileNguoiDung.matKhau = resultSet.getString("MATKHAU");


                            //Profile.email = taiKhoan;  //--Lấy tài khoản gán cho biến static dùng trong Profile

                            String ten = resultSet.getString("TEN");  //--Lấy dữ liệu từ cột TEN trong bảng TAIKHOAN_TV dùng trong Profile
                            ProfileNguoiDung.ten = ten; //-- gán biến nd_HoTen ở Profile bằng biến được lấy ra từ cột TEN (biến ten)

                            String sDT = resultSet.getString("DIENTHOAI"); //---tương tự ở trên
                            ProfileNguoiDung.soDt = sDT;

                        }
                        else {
                            Toast.makeText(v.getContext(), "Không tồn tại tài khoản", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("ASK", "Connection Called" + e.getMessage());
                    }
                }
            }
        });


        email.setAlpha(v);
        password.setAlpha(v);
        forget.setAlpha(v);
        login.setAlpha(v);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forget.setTranslationX(800);
        login.setTranslationX(800);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        return root;
    }
}
