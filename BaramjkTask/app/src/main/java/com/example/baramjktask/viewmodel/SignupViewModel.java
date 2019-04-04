package com.example.baramjktask.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.baramjktask.dto.Resource;
import com.example.baramjktask.model.SignupRequest;
import com.example.baramjktask.model.SignupResponse;
import com.example.baramjktask.repository.SignupRepository;
import com.google.gson.JsonObject;

import java.util.Map;

public class SignupViewModel extends ViewModel {
    private MutableLiveData<Request> signupRequest = new MutableLiveData<>();
    private LiveData<Resource<JsonObject>> signupResult = Transformations.switchMap(signupRequest, new Function<Request, LiveData<Resource<JsonObject>>>() {
        @Override
        public LiveData<Resource<JsonObject>> apply(Request input) {
            LiveData<Resource<JsonObject>> resourceLiveData = new SignupRepository().callSignupApi(input.option);
            return resourceLiveData;
        }
    });
    public LiveData<Resource<JsonObject>> getSignupData(){

        return this.signupResult;
    }

    public static class Request {
        final private Map<String,String> option;
        public Request(Map<String,String> option) {
            this.option = option;

        }
    }

    public void callSignupApi(Map<String,String> option){
        signupRequest.setValue(new Request(option));
    }
}
