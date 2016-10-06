package jarmandocordova.masterdetail.global.data;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by jarma on 10/5/2016.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        System.out.println(
                String.format("Enviando request %s a %s%n%s", request.url(), chain.connection(),
                        request.headers()));

        Response response = chain.proceed(request);

        Log.d("response", response.toString());

        long t2 = System.nanoTime();
        System.out.println(
                String.format("Se recibió el response para %s en %.1fms%n%s", response.request().url(),
                        (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}