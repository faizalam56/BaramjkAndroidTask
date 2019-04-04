package com.example.baramjktask.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.baramjktask.dto.Resource;
import com.example.baramjktask.exception.AppException;
import com.example.baramjktask.network.APIClient;
import com.example.baramjktask.network.APIInterface;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private APIInterface apiInterface;

    public LoginRepository(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public MutableLiveData<Resource<JsonObject>> callLoginApi(Map<String,String> option){
        final MutableLiveData<Resource<JsonObject>> loginLiveData = new MutableLiveData<>();
        loginLiveData.setValue(Resource.<JsonObject>loading());

        Call<JsonObject> call = apiInterface.loginCall(option);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body()!=null){
                    JsonObject resource = response.body();

                    if (!resource.get("details").isJsonArray()){
                        loginLiveData.setValue(Resource.<JsonObject>success(resource));
                    }else{
                        loginLiveData.setValue(Resource.<JsonObject>error(null,resource));
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                call.cancel();
                loginLiveData.setValue(Resource.<JsonObject>error(new AppException(t),null));
            }
        });
        return loginLiveData;
    }
}
