package com.example.baramjktask.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.baramjktask.dto.Resource;
import com.example.baramjktask.repository.LoginRepository;
import com.google.gson.JsonObject;

import java.util.Map;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Request> loginRequest = new MutableLiveData<>();
    private LiveData<Resource<JsonObject>> loginResult = Transformations.switchMap(loginRequest, new Function<Request, LiveData<Resource<JsonObject>>>() {
        @Override
        public LiveData<Resource<JsonObject>> apply(Request input) {
            LiveData<Resource<JsonObject>> resourceLiveData = new LoginRepository().callLoginApi(input.option);
            return resourceLiveData;
        }
    });
    public LiveData<Resource<JsonObject>> getLoginData(){

        return this.loginResult;
    }

    public static class Request {
        final private Map<String,String> option;
        public Request(Map<String,String> option) {
            this.option = option;

        }
    }

    public void callLoginApi(Map<String,String> option){
        loginRequest.setValue(new Request(option));
    }
}
