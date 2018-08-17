package xyz.appmaker.keralarescue.Tools;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xyz.appmaker.keralarescue.Models.UserResponse;

public interface APIService {
    @POST("api/1/rest-auth/login/")
    Call<UserResponse> login();
}
