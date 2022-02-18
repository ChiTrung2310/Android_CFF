package com.example.cffapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


public class XacNhanDonHang extends Fragment {
    ArrayList<OrderListModel> orderListModel = ComfirmOrderActivityAdapter.orderListModels;
    EditText ghiChu;
    TextView diaChi, soLuong,tongtien, total,tongtiensau;
    float thanhTien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_xac_nhan_don_hang,container,false);
        ghiChu = root.findViewById(R.id.textview_ghichu);
        ghiChu.setText(OrderThongTIn.ghiChu);
        diaChi = root.findViewById(R.id.textview_diadiem);
        diaChi.setText(OrderThongTIn.diaChi);
        soLuong = root.findViewById(R.id.textview_soluong);
        int soluong = 0;
        total = root.findViewById(R.id.textview_TT);
        total.setText(""+tongtien); // gán tổng tiền dưới cùng (sau cùng)


        soLuong = root.findViewById(R.id.textview_soluong);
        for (OrderListModel od:orderListModel
             ) {
            soluong += od.getSoluong();
            thanhTien = thanhTien + (od.getSoluong()*od.getPrice());

        }
        soLuong.setText(soluong +" món");
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        tongtien = root.findViewById(R.id.textview_tongtien);
        int tong = (int)thanhTien;
        String tongtTien = formatter.format(tong);

        tongtien.setText("Tổng tiền: "+ tongtTien +" VND");
        tongtiensau = root.findViewById(R.id.textview_TT);
        tongtiensau.setText(tongtTien +" VND");

        Button dH = (Button) root.findViewById(R.id.button_dathang);
        dH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random generator = new Random();
                int ms = generator.nextInt(1000000000);
                //----Lấy ngày tháng hiện tại của hệ thống
                java.util.Date date = new java.util.Date();
                java.sql.Date currentTime = new java.sql.Date(date.getTime());

                if(ms != 0  && OrderThongTIn.Ten != null && OrderThongTIn.soDienThoai != null && currentTime != null && OrderThongTIn.diaChi != null ) {
                    if(ProfileNguoiDung.ten == null)
                        ProfileNguoiDung.ten = OrderThongTIn.Ten; //---Không đăng nhập lấy tên trên edit hiện hành nhập tên
//                    String query1 = "Insert into DON_HANG (MSDH,MSKH,TENKH,SODT,NGAYDAT,KICHHOAT,DIACHI,GHICHU) values (?,?,?,?,?,?,?,?)";
//                    themDonHang(ms, ProfileNguoiDung.ten, OrderThongTIn.Ten, OrderThongTIn.soDienThoai, currentTime, 0, OrderThongTIn.diaChi, ghiChu.getText().toString(), query1, v.getContext());
                    String tenma = "";
                    int soLuong = 0,giaban = 0 ;
                    for (OrderListModel od:orderListModel
                         ) {
                        tenma = od.getName();
                        soLuong = od.getSoluong();
                        giaban =(int) od.getPrice();
                        themDonHang(ms, ProfileNguoiDung.ten, OrderThongTIn.Ten, OrderThongTIn.soDienThoai, currentTime, 0, OrderThongTIn.diaChi, ghiChu.getText().toString(),tenma,soLuong,giaban,0,(int)thanhTien, v.getContext());

                    }
                    ComfirmOrderActivityAdapter.orderListModels.clear();
                    Toast.makeText(v.getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), Dashboard.class);
                    v.getContext().startActivity(intent);


                }else {
                    Toast.makeText(v.getContext(), "Đặt hàng thất bại! Thiếu thông tin giao hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;

    }

    public void themDonHang(int msdh, String mskh, String tenkh, String soDT, Date ngaydat, int kichhoat, String diachi, String ghichu, String tenma, int soluong, int giaban, int giamgia, int thanhtien, Context x){
        Connection conn = SERVER.Connect();
        if (conn == null) {

        } else {

            String sql = "{call prThemHD (?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cstm;
            String kq;
            try{
                cstm = conn.prepareCall(sql);
                cstm.setInt(1,msdh);
                cstm.setString(2,mskh);
                cstm.setString(3,tenkh);
                cstm.setString(4,soDT);
                cstm.setDate(5,ngaydat);
                cstm.setInt(6,kichhoat);
                cstm.setString(7,diachi);
                cstm.setString(8,ghichu);

                cstm.setString(9,tenma);
                cstm.setInt(10,soluong);
                cstm.setInt(11,giaban);
                cstm.setInt(12,giamgia);
                cstm.setInt(13,thanhtien);

                cstm.execute();
                cstm.close();
                conn.close();

            }catch (SQLException e){
                e.printStackTrace();
            }


//                query1 = "Insert into donHang (MSDH,MSKH,NGAYDAT,KICHHOAT,DIACHI,GHICHU) values (?,?,?,?,?,?)";
//            String sqlquery = query1;
//            //----Dùng parameter chỗ này nghe vũ huy
//            try (PreparedStatement pstmt = conn.prepareStatement(query1)) {
//                pstmt.setInt(1, msdh);
//                pstmt.setString(2, mskh);
//                pstmt.setString(3, tenkh);
//                pstmt.setString(4, soDT);
//                pstmt.setDate(5, ngaydat);
//                pstmt.setInt(6, kichhoat);
//                pstmt.setString(7, diachi);
//                pstmt.setString(8, ghichu);
//
//                int result = pstmt.executeUpdate();  //---Khai báo result là kiểu int để nhận hàm executeUpdate()
//
//                if (result > 0) {
//                    Intent intent = new Intent(x, Dashboard.class);
//                    x.startActivity(intent);
//
//                    Toast.makeText(x, "Đặt hàng Thành công", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(x, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                Log.e("ASK", "Connection Called" + e.getMessage());
//            }
        }
    }
}