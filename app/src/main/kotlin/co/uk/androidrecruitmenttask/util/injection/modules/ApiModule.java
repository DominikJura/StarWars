package co.uk.androidrecruitmenttask.util.injection.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import co.uk.androidrecruitmenttask.BuildConfig;
import co.uk.androidrecruitmenttask.util.api.StarWarsService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.uk.androidrecruitmenttask.util.configuration.StringConstanst.ENDPOINT;

/**
 * Created by filipradon on 15/07/16.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC
                : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory providesRx2JavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient,
                              RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {

        return new Retrofit.Builder().addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    StarWarsService provideStarWarsService(Retrofit retrofit) {
        return retrofit.create(StarWarsService.class);
    }
}
