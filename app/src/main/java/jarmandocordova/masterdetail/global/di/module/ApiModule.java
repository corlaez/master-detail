package jarmandocordova.masterdetail.global.di.module;

import dagger.Module;
import dagger.Provides;
import jarmandocordova.masterdetail.global.data.belatrixsf.BelatrixsfApi;
import retrofit.Retrofit;

/**
 * Created by jarma on 10/5/2016.
 */
@Module(includes = NetworkModule.class)
public class ApiModule {

    @Provides
    public BelatrixsfApi providesBelatrixsfApi(Retrofit.Builder retrofitBuilder){
        return retrofitBuilder.baseUrl(BelatrixsfApi.END).build().create(BelatrixsfApi.class);
    }

}
