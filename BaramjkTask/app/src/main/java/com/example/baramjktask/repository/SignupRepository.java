package com.example.baramjktask.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.baramjktask.dto.Resource;
import com.example.baramjktask.exception.AppException;
import com.example.baramjktask.model.SignupRequest;
import com.example.baramjktask.model.SignupResponse;
import com.example.baramjktask.network.APIClient;
import com.example.baramjktask.network.APIInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupRepository {
    private APIInterface apiInterface;
    public SignupRepository(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public MutableLiveData<Resource<JsonObject>> callSignupApi(Map<String,String> option){
        final MutableLiveData<Resource<JsonObject>> signupLiveData = new MutableLiveData<>();
        signupLiveData.setValue(Resource.<JsonObject>loading());

        Call<JsonObject> call = apiInterface.signupCall(option);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body()!=null){
                    JsonObject resource = response.body();

                    if (!resource.get("details").isJsonArray()){
                        signupLiveData.setValue(Resource.<JsonObject>success(resource));
                    }else{
                        signupLiveData.setValue(Resource.<JsonObject>error(null,resource));
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                call.cancel();
                signupLiveData.setValue(Resource.<JsonObject>error(new AppException(t),null));
            }
        });
        return signupLiveData;
    }


}
