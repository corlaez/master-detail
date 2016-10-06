package jarmandocordova.masterdetail.global.di.module;

import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import jarmandocordova.masterdetail.global.data.belatrixsf.BelatrixsfApi;
import jarmandocordova.masterdetail.global.data.belatrixsf.Data;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by jarma on 10/5/2016.
 */
@Module(includes = NetworkModule.class)
public class ApiModule {

    @Provides @Named("belatrixsf.api")
    public BelatrixsfApi providesBelatrixsfApi(Retrofit.Builder retrofitBuilder){
        BelatrixsfApi ba = retrofitBuilder.baseUrl(BelatrixsfApi.END).build().create(BelatrixsfApi.class);
        /*if (BuildConfig.DEBUG) {
            // do something for a debug build
            Log.d("mockApi,"Using mocked api");
            ba = ba == null? providesBelatrixsfApiMock() : ba;
        }*/
        Log.d("belatrixsf.api","using belatrixsf.api");
        return ba;
    }

    @Provides @Named("belatrixsf.api.mock")
    public BelatrixsfApi providesBelatrixsfApiMock(){
        Log.d("belatrixsf.api.mock","using belatrixsf.api.mock");
        return new BelatrixsfApi() {
            @Override
            public Observable<Data> getData() {
                return Observable.just(new Gson().fromJson(BelatrixsfApi.EXAMPLE, Data.class));
            }
        };
    }

}
