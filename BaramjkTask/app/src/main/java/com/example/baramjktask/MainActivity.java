package com.example.baramjktask;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baramjktask.viewbinding.MainViewBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewBinding mainViewBinding;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        mainViewBinding = new MainViewBinding(getLayoutInflater(),null,fragmentManager);

        setContentView(mainViewBinding.getRootView());
    }
}
