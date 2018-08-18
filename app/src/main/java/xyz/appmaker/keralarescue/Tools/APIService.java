package xyz.appmaker.keralarescue.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import xyz.appmaker.keralarescue.Models.PersonsResponse;
import xyz.appmaker.keralarescue.Models.UserLogin;
import xyz.appmaker.keralarescue.Models.UserResponse;
import xyz.appmaker.keralarescue.Room.Camp.CampNames;
import xyz.appmaker.keralarescue.Room.CampDatabase;
import xyz.appmaker.keralarescue.Room.PersonData.PersonDataEntity;

public interface APIService {
    @POST("api/1/rest-auth/login/")
    Call<UserResponse> login(@Body UserLogin userLogin);

    @GET("api/1/camps/")
    Call<List<CampNames>> getCampList(@Header("Authorization") String authorization);

    @POST("api/1/persons/")
    Call<PersonsResponse> addPersons(@Header("Authorization") String authorization, @Body List<PersonDataEntity> personDataEntities);


}
