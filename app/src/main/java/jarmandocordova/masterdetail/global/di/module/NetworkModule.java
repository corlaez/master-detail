package jarmandocordova.masterdetail.global.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jarma on 10/5/2016.
 */
@Module
public class NetworkModule {

    @Provides
    public Interceptor providesInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    public OkHttpClient providesOkHttpClient(Interceptor interceptor){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();
        return okHttpClient;
    }

    @Provides
    public Converter.Factory providesConverterFactory(){
        //https://github.com/square/retrofit/issues/1676
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Converter.Factory cf = GsonConverterFactory.create(gson);
        return cf;
    }

    @Provides
    public Retrofit.Builder providesRetrofitBuilder(OkHttpClient okHttpClient, Converter.Factory cf){
        return new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(cf)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

}
