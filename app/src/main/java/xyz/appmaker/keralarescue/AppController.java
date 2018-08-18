package xyz.appmaker.keralarescue;

import android.app.Application;

import com.onesignal.OneSignal;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

    }

    public static APIService getRetrofitInstance() {
        if (retrofit ==  null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!

            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(APIService.class);
    }
}
