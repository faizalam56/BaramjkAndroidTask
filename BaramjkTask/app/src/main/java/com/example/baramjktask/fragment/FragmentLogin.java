package com.example.baramjktask.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baramjktask.Globals;
import com.example.baramjktask.R;
import com.example.baramjktask.databinding.FragmentLoginBinding;
import com.example.baramjktask.dto.Resource;
import com.example.baramjktask.model.LoginResponse;
import com.example.baramjktask.model.LoginResponseFail;
import com.example.baramjktask.model.SignupResponse;
import com.example.baramjktask.model.SignupResponseFail;
import com.example.baramjktask.viewmodel.LoginViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentLogin extends Fragment {

    private FragmentLoginBinding fragmentLoginBinding;
    private View rootView;
    private Globals mGlobals;
    private LoginViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlobals = new Globals();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
        rootView = fragmentLoginBinding.getRoot();

        fragmentLoginBinding.setFragmentLogin(this);
        loginVMProvider();
        return rootView;
    }

    private void loginVMProvider(){
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.getLoginData().observe(this, new Observer<Resource<JsonObject>>() {
            @Override
            public void onChanged(@Nullable Resource<JsonObject> jsonObjectResource) {
                processResponse(jsonObjectResource);
            }
        });
    }

    private void processResponse(Resource resource) {
        Gson gson = new Gson();
        switch (resource.getStatus()) {
            case LOADING:
                showProgress(true);
                break;
            case SUCCESS: {
                showProgress(false);
                JsonObject jsonObject= (JsonObject) resource.getData();
                if(!jsonObject.get("details").isJsonArray()) {
                    LoginResponse resourceData = gson.fromJson(jsonObject.toString(),LoginResponse.class);
                    mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                }else{
                    LoginResponseFail resourceData = gson.fromJson(jsonObject.toString(), LoginResponseFail.class);
                    mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                }
                break;
            }
            case ERROR:
                showProgress(false);
                JsonObject jsonObject= (JsonObject) resource.getData();
                if(jsonObject!=null) {
                    if (!jsonObject.get("details").isJsonArray()) {
                        LoginResponse resourceData = gson.fromJson(jsonObject.toString(), LoginResponse.class);
                        mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                    } else {
                        LoginResponseFail resourceData = gson.fromJson(jsonObject.toString(), LoginResponseFail.class);
                        mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                    }
                }else {
                    mGlobals.showAlertDialog("Request Failed Try again....", getContext());
                }
                break;
        }
    }

    private void showProgress(Boolean loading){
        fragmentLoginBinding.setLoading(loading);
    }

    public void signupFragment(){
        FragmentSignup fragmentSignup = new FragmentSignup();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,fragmentSignup)
                .addToBackStack("faiz")
                .commit();
    }

    public void login(String email,String password){
        if(mGlobals.isNetworkConnected(getActivity())){
            if (email == null || email.length() == 0) {
                Toast.makeText(getContext(), "please enter email", Toast.LENGTH_SHORT).show();
            } else if (password == null || password.length() == 0) {
                Toast.makeText(getContext(), "please enter lastName", Toast.LENGTH_SHORT).show();
            }else{
                viewModel.callLoginApi(getQueryMap(email,password));
            }
        }else{
            mGlobals.showSnakeBarMessage(fragmentLoginBinding.clMainLayout,getActivity());
        }
    }

    private Map<String,String> getQueryMap(String email,String password){
        Map<String,String> data = new HashMap<>();
        data.put("email_address",email);
        data.put("password",password);
        data.put("json","true");

        return data;
    }
}
