package com.example.baramjktask.viewbinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baramjktask.R;
import com.example.baramjktask.databinding.ActivityMainBinding;
import com.example.baramjktask.fragment.FragmentLogin;
import com.example.baramjktask.iface.GuiView;

public class MainViewBinding implements GuiView {

    private ActivityMainBinding binding;
    private View rootView;
    private FragmentManager fragmentManager;
    public MainViewBinding(LayoutInflater inflater, ViewGroup viewGroup, FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main,viewGroup,false);
        rootView = binding.getRoot();

        fragmentTransaction();
    }
    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    private void fragmentTransaction(){
        FragmentLogin fragmentLogin = new FragmentLogin();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_container,fragmentLogin);
        fragmentTransaction.commit();
    }
}
