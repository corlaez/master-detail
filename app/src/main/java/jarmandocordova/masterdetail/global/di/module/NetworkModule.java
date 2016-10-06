package jarmandocordova.masterdetail.global.di.module;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import jarmandocordova.masterdetail.global.data.LoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by jarma on 10/5/2016.
 */
@Module
public class NetworkModule {

    @Provides
    public Interceptor providesInterceptor(){
        return new LoggingInterceptor();
    }

    @Provides
    public OkHttpClient providesOkHttpClient(Interceptor interceptor){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(interceptor);
        return okHttpClient;
    }

    @Provides
    public Retrofit.Builder providesRetrofitBuilder(OkHttpClient okHttpClient){
        return new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

}
