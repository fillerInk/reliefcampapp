package xyz.appmaker.keralarescue.Tools;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xyz.appmaker.keralarescue.Models.UserLogin;
import xyz.appmaker.keralarescue.Models.UserResponse;

public interface APIService {
    @POST("api/1/rest-auth/login/")
    Call<UserResponse> login(@Body UserLogin userLogin);
//    Call<UserResponse> login(@Field("username") String username, @Field("password") String password);
}
