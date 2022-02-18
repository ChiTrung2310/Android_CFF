package com.example.cffapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignupTabFragment extends Fragment {

    EditText email_sgup,hoten, sodt, matkhau;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signups_layout_fragment,container,false);
        email_sgup = root.findViewById(R.id.email_signup);
        hoten = root.findViewById(R.id.hoten_signup);
        sodt = root.findViewById(R.id.mobile_signup);
        matkhau = root.findViewById(R.id.pass_signup);


        Button dangKy = root.findViewById(R.id.buttom_dk);
        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = hoten.getText().toString();
                String email = email_sgup.getText().toString();
                String soDT = sodt.getText().toString();
                String matKhau = matkhau.getText().toString();
                int quyen = 0;

                if (hoTen.length() > 0 && email.length() > 0 && soDT.length() > 0 && matKhau.length() > 0) {
                    //---Truyền tham số câu query vào hàm/ câu query có thể chỉnh lại tùy bảng
                    String query1 = "Insert into TAI_KHOAN_TV (TEN,EMAIL,DIENTHOAI,MATKHAU,QUYENHAN) values (?,?,?,?,?)";
                    Sigup(query1, hoTen,email,soDT,matKhau,quyen,v.getContext());
                }
                else {
                    Toast.makeText(v.getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    hoten.requestFocus();
                }
            }
        });



        return root;
    }
    private void Sigup(String query1, String hoTen, String email, String soDT, String matKhau, int quyen, Context x) {
        Connection conn = SERVER.Connect();
        if (conn == null) {

        } else {
//                query1 = "Insert into TAI_KHOAN_TV (TEN,EMAIL,DIENTHOAI,MATKHAU,QUYENHAN) values (?,?,?,?,?)";
            String sqlquery = query1;
            //----Dùng parameter chỗ này nghe vũ huy
            try (PreparedStatement pstmt = conn.prepareStatement(query1)) {
                pstmt.setString(1, hoTen);
                pstmt.setString(2, email);
                pstmt.setString(3, soDT);
                pstmt.setString(4, matKhau);
                pstmt.setInt(5, quyen);

                int result = pstmt.executeUpdate();  //---Khai báo result là kiểu int để nhận hàm executeUpdate()

                if (result > 0) {
                    Intent intent = new Intent(x, Login.class);
                    x.startActivity(intent);

                    Toast.makeText(x, "Đăng ký Thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(x, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("ASK", "Connection Called" + e.getMessage());
            }
        }
    }
}
