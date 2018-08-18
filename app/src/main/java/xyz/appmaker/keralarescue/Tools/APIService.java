package xyz.appmaker.keralarescue.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.appmaker.keralarescue.Models.PersonsResponse;
import xyz.appmaker.keralarescue.Models.UpdateCamp;
import xyz.appmaker.keralarescue.Models.UserLogin;
import xyz.appmaker.keralarescue.Models.UserResponse;
import xyz.appmaker.keralarescue.Room.Camp.CampNames;
import xyz.appmaker.keralarescue.Room.CampDatabase;
import xyz.appmaker.keralarescue.Room.PersonData.PersonDataEntity;

public interface APIService {
    @POST("api/1/rest-auth/login/")
    Call<UserResponse> login(@Body UserLogin userLogin);

    @GET("api/1/camplist/")
    Call<List<CampNames>> getCampList(@Header("Authorization") String authorization, @Query("district") String district);

    @POST("api/1/persons/")
    Call<PersonsResponse> addPersons(@Header("Authorization") String authorization, @Body List<PersonDataEntity> personDataEntities);

    @GET("api/1/camplist/{id}")
    Call<CampNames> getCamp(@Header("Authorization") String authorization, @Path("id") String id);

    @PUT("api/1/camplist/{id}")
    Call<CampNames> updateCamp(@Header("Authorization") String authorization, @Path("id") String id, @Body UpdateCamp updateCamp);


}
