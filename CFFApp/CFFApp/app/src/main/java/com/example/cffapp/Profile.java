package com.example.cffapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Profile extends AppCompatActivity {

    ImageView edit,back;
    Button update;
    TextInputEditText name, email, phone, pass;
    TextView ten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edit = findViewById(R.id.edit_profile);
        update = findViewById(R.id.update_btn);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        phone = findViewById(R.id.profile_phonenume);
        pass = findViewById(R.id.profile_pass);
        back = findViewById(R.id.return_profile);
        ten = findViewById(R.id.tenL);

        name.setText(ProfileNguoiDung.ten);
        ten.setText(ProfileNguoiDung.ten);
        email.setText(ProfileNguoiDung.email);
        phone.setText(ProfileNguoiDung.soDt);
        pass.setText(ProfileNguoiDung.matKhau);

        name.setEnabled(false);
        phone.setEnabled(false);
        email.setEnabled(false);
        pass.setEnabled(false);

        update.setVisibility(View.INVISIBLE);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                phone.setEnabled(true);
                email.setEnabled(true);
                pass.setEnabled(true);

                update.setVisibility(View.VISIBLE);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(false);
                phone.setEnabled(false);
                email.setEnabled(false);
                pass.setEnabled(false);
                update.setVisibility(View.INVISIBLE);
                Connection conn = SERVER.Connect(); //--Xử dụng conn

                String hoTen = name.getText().toString();
                String dc_email = email.getText().toString();
                String soDT = phone.getText().toString();
                int quyen = 0;

                if (hoTen.length() > 0 && email.length() > 0 && soDT.length() > 0) {
                    //--Sử dụng hàm update dữ liệu người dùng
                    updateTT(hoTen,soDT,dc_email,v.getContext());
                } else {
                    Toast.makeText(v.getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void updateTT(String hoTen, String soDT, String dc_email, Context x) {
        Connection conn = SERVER.Connect();
        if (conn == null) {
            Toast.makeText(x, "", Toast.LENGTH_SHORT).show();
        } else {
            String query1 = "UPDATE TAI_KHOAN_TV set TEN = ? , DIENTHOAI = ? where EMAIL = ?";
            //----Dùng parameter chỗ này nghe vũ huy
            try (PreparedStatement pstmt = conn.prepareStatement(query1)) {
                pstmt.setString(1, hoTen);
                pstmt.setString(2, soDT);
                pstmt.setString(3, dc_email);

                int result = pstmt.executeUpdate();  //---Khai báo result là kiểu int để nhận hàm executeUpdate()

                if (result > 0) {
                    Intent intent = new Intent(x, Profile.class);
                    startActivity(intent);
                    Toast.makeText(x, "Update Thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(x, "Update thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("ASK", "Connection Called" + e.getMessage());
            }
        }
    }
}