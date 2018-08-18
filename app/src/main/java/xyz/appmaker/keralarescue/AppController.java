package xyz.appmaker.keralarescue;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.appmaker.keralarescue.Tools.APIService;

public class AppController extends Application {
public static APIService RetrofitService;
   static  Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
         retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.102:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static APIService getRetrofitInstance(){
        return retrofit.create(APIService.class);
    }
}
