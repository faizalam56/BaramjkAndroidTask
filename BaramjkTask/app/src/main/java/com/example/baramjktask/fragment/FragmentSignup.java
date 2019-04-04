package com.example.baramjktask.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baramjktask.Globals;
import com.example.baramjktask.R;
import com.example.baramjktask.databinding.FragmentSignupBinding;
import com.example.baramjktask.dto.Resource;
import com.example.baramjktask.model.SignupRequest;
import com.example.baramjktask.model.SignupResponse;
import com.example.baramjktask.model.SignupResponseFail;
import com.example.baramjktask.viewmodel.SignupViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentSignup extends Fragment {
    private FragmentSignupBinding fragmentSignupBinding;
    private View rootView;
    private Globals mGlobals;
    private String deviceId;
    private SignupViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlobals = new Globals();
        deviceId = getDeviceId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSignupBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup,container,false);
        rootView = fragmentSignupBinding.getRoot();

        fragmentSignupBinding.setFragmentSignup(this);
        signupVMProvider();
        return rootView;
    }

    private void signupVMProvider(){
        viewModel = ViewModelProviders.of(this).get(SignupViewModel.class);
        viewModel.getSignupData().observe(this, new Observer<Resource<JsonObject>>() {
            @Override
            public void onChanged(@Nullable Resource<JsonObject> signupResponseResource) {
                processResponse(signupResponseResource);
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
                    SignupResponse resourceData = gson.fromJson(jsonObject.toString(),SignupResponse.class);
                    mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                }else{
                    SignupResponseFail resourceData = gson.fromJson(jsonObject.toString(),SignupResponseFail.class);
                    mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                }
                break;
            }
            case ERROR:
                showProgress(false);
                JsonObject jsonObject= (JsonObject) resource.getData();
                if(jsonObject!=null) {
                    if (!jsonObject.get("details").isJsonArray()) {
                        SignupResponse resourceData = gson.fromJson(jsonObject.toString(), SignupResponse.class);
                        mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                    } else {
                        SignupResponseFail resourceData = gson.fromJson(jsonObject.toString(), SignupResponseFail.class);
                        mGlobals.showAlertDialog(resourceData.getMsg(), getContext());
                    }
                }else {
                    mGlobals.showAlertDialog("Request Failed Try again....", getContext());
                }
                break;
        }
    }

    private void showProgress(Boolean loading){
        fragmentSignupBinding.setLoading(loading);
    }

    public void onSignup(String firstName,String lastName,String email,String password,String contact){
        if(mGlobals.isNetworkConnected(getActivity())){
            if (firstName == null || firstName.length() == 0) {
                Toast.makeText(getContext(), "please enter firstName", Toast.LENGTH_SHORT).show();
            } else if (lastName == null || lastName.length() == 0) {
                Toast.makeText(getContext(), "please enter lastName", Toast.LENGTH_SHORT).show();
            }else if (email == null || email.length() == 0) {
                Toast.makeText(getContext(), "please enter email", Toast.LENGTH_SHORT).show();
            }else if (password == null || password.length() == 0) {
                Toast.makeText(getContext(), "please enter password", Toast.LENGTH_SHORT).show();
            }else if (contact == null || contact.length() == 0) {
                Toast.makeText(getContext(), "please enter contact", Toast.LENGTH_SHORT).show();
            }else{
                viewModel.callSignupApi(getQueryMap(firstName,lastName,email,password,contact));
            }
        }else{
            mGlobals.showSnakeBarMessage(fragmentSignupBinding.clMainLayout,getActivity());
        }
    }


    private Map<String,String> getQueryMap(String firstName,String lastName,String email,String password,String contact){
        Map<String, String> data = new HashMap<>();
        data.put("json", "true");
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("email_address", email);
        data.put("password", password);
        data.put("contact_phone", contact);
        data.put("device_id", deviceId);
        data.put("app_version", "1");
        data.put("device_platform", "Android");

        return data;
    }

    private String getDeviceId(){
        String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }
}
