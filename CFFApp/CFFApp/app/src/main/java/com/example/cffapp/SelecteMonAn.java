package com.example.cffapp;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelecteMonAn {
    public static ArrayList<DymanicRVModel> chicken(){
        ArrayList<DymanicRVModel> items = new ArrayList<>();

        try {
            Connection con = SERVER.Connect(); //khởi tạo kết nối tới server, SERVER chính là class riêng, tìm trong table java
            if (con == null) {
                Log.e ("ERROR", "Không thể kết nối với Server"); //Tiếng Việt :D
            } else {
                String sql = "SELECT TENMA, DONGIA,HINH FROM MON_AN WHERE MALOAI = 1";
                //trên đây là câu truy vấn
                Statement stmt = con.createStatement(); //blah blah blah
                ResultSet resultSet = stmt.executeQuery(sql); //thực thi và trả về một cục ResultSet, nó là gì thì Google, tui chịu

                while (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
                {
                   items.add(new DymanicRVModel(resultSet.getString("TENMA"),resultSet.getFloat("DONGIA"),R.drawable.chicken_food));

                }
                con.close();//Đấm vỡ mồm SERVER xong thì phải băng bó cho nó.
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }
    public static ArrayList<DymanicRVModel> burger(){
        ArrayList<DymanicRVModel> items = new ArrayList<>();

        try {
            Connection con = SERVER.Connect(); //khởi tạo kết nối tới server, SERVER chính là class riêng, tìm trong table java
            if (con == null) {
                Log.e ("ERROR", "Không thể kết nối với Server"); //Tiếng Việt :D
            } else {
                String sql = "SELECT TENMA, DONGIA,HINH FROM MON_AN WHERE MALOAI = 2";
                //trên đây là câu truy vấn
                Statement stmt = con.createStatement(); //blah blah blah
                ResultSet resultSet = stmt.executeQuery(sql); //thực thi và trả về một cục ResultSet, nó là gì thì Google, tui chịu

                while (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
                {
                    items.add(new DymanicRVModel(resultSet.getString("TENMA"),resultSet.getFloat("DONGIA"),R.drawable.buger));

                }
                con.close();//Đấm vỡ mồm SERVER xong thì phải băng bó cho nó.
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }
    public static ArrayList<DymanicRVModel> drink(){
        ArrayList<DymanicRVModel> items = new ArrayList<>();

        try {
            Connection con = SERVER.Connect(); //khởi tạo kết nối tới server, SERVER chính là class riêng, tìm trong table java
            if (con == null) {
                Log.e ("ERROR", "Không thể kết nối với Server"); //Tiếng Việt :D
            } else {
                String sql = "SELECT TENMA, DONGIA,HINH FROM MON_AN WHERE MALOAI = 3";
                //trên đây là câu truy vấn
                Statement stmt = con.createStatement(); //blah blah blah
                ResultSet resultSet = stmt.executeQuery(sql); //thực thi và trả về một cục ResultSet, nó là gì thì Google, tui chịu

                while (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
                {
                    items.add(new DymanicRVModel(resultSet.getString("TENMA"),resultSet.getFloat("DONGIA"),R.drawable.milktea));

                }
                con.close();//Đấm vỡ mồm SERVER xong thì phải băng bó cho nó.
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }
    public static ArrayList<DymanicRVModel> combo(){
        ArrayList<DymanicRVModel> items = new ArrayList<>();

        try {
            Connection con = SERVER.Connect(); //khởi tạo kết nối tới server, SERVER chính là class riêng, tìm trong table java
            if (con == null) {
                Log.e ("ERROR", "Không thể kết nối với Server"); //Tiếng Việt :D
            } else {
                String sql = "SELECT TENMA, DONGIA,HINH FROM MON_AN WHERE MALOAI = 4";
                //trên đây là câu truy vấn
                Statement stmt = con.createStatement(); //blah blah blah
                ResultSet resultSet = stmt.executeQuery(sql); //thực thi và trả về một cục ResultSet, nó là gì thì Google, tui chịu

                while (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
                {
                    items.add(new DymanicRVModel(resultSet.getString("TENMA"),resultSet.getFloat("DONGIA"),R.drawable.combo));

                }
                con.close();//Đấm vỡ mồm SERVER xong thì phải băng bó cho nó.
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }
    public static ArrayList<DymanicRVModel> fries(){
        ArrayList<DymanicRVModel> items = new ArrayList<>();

        try {
            Connection con = SERVER.Connect(); //khởi tạo kết nối tới server, SERVER chính là class riêng, tìm trong table java
            if (con == null) {
                Log.e ("ERROR", "Không thể kết nối với Server"); //Tiếng Việt :D
            } else {
                String sql = "SELECT TENMA, DONGIA,HINH FROM MON_AN WHERE MALOAI = 5";
                //trên đây là câu truy vấn
                Statement stmt = con.createStatement(); //blah blah blah
                ResultSet resultSet = stmt.executeQuery(sql); //thực thi và trả về một cục ResultSet, nó là gì thì Google, tui chịu

                while (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
                {
                    items.add(new DymanicRVModel(resultSet.getString("TENMA"),resultSet.getFloat("DONGIA"),R.drawable.fries));

                }
                con.close();//Đấm vỡ mồm SERVER xong thì phải băng bó cho nó.
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }
    public static ArrayList<DymanicRVModel> search(String canTim){
        ArrayList<DymanicRVModel> items = new ArrayList<>();

        try {
            Connection con = SERVER.Connect(); //khởi tạo kết nối tới server, SERVER chính là class riêng, tìm trong table java
            if (con == null) {
                Log.e ("ERROR", "Không thể kết nối với Server"); //Tiếng Việt :D
            } else {
                String sql = "SELECT TENMA, DONGIA,HINH FROM MON_AN WHERE TENMA like '%"+ canTim+"%'";
                //trên đây là câu truy vấn
                Statement stmt = con.createStatement(); //blah blah blah
                ResultSet resultSet = stmt.executeQuery(sql); //thực thi và trả về một cục ResultSet, nó là gì thì Google, tui chịu

                while (resultSet.next())//nếu trong resultset không null thì sẽ trả về True
                {
                    items.add(new DymanicRVModel(resultSet.getString("TENMA"),resultSet.getFloat("DONGIA"),R.drawable.milktea));
                    ///hình add lỗi vì chưa biết lưu hình như thế nào.

                }
                con.close();//Đấm vỡ mồm SERVER xong thì phải băng bó cho nó.
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }
}
