package xyz.appmaker.keralarescue;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.appmaker.keralarescue.Tools.APIService;
import xyz.appmaker.keralarescue.Tools.Config;

public class AppController extends Application {
    public static APIService RetrofitService;
    static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static APIService getRetrofitInstance() {
        if (retrofit ==  null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(APIService.class);
    }
}
