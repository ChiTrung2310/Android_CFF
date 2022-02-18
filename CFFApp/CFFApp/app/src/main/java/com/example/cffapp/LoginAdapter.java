package com.example.cffapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class LoginAdapter extends FragmentStatePagerAdapter {

    private Context context;
    int totalTabs;

    public LoginAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public  LoginAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }
    public Fragment getItem(int position) {
        switch (position){
            case 0:
//                LoginTabFragment loginTabFragment = new LoginTabFragment();
//                return loginTabFragment;
                return  new LoginTabFragment();
            case 1:
                SignupTabFragment signupTabFragment = new SignupTabFragment();
                return  signupTabFragment;
            default:
                return new SignupTabFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
            case 0:
                title = "Đăng nhập";
                break;
            case 1:
                title = "Đăng kí";
                break;
        }
        return title;
    }
}
