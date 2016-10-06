package jarmandocordova.masterdetail.main.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jarmandocordova.masterdetail.global.MyApplication;
import jarmandocordova.masterdetail.global.data.belatrixsf.BelatrixsfApi;
import jarmandocordova.masterdetail.global.data.belatrixsf.Data;
import jarmandocordova.masterdetail.global.db.LocalCache;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jarma on 10/5/2016.
 */

public class MainGateway {
    private BelatrixsfApi belatrixsfApi;
    private LocalCache localCache;

    public MainGateway() {
        belatrixsfApi = MyApplication.getBaseComponent().getBelatrixsfApi();
        localCache = MyApplication.getBaseComponent().getLocalCache();
        if (belatrixsfApi == null || localCache == null) {
            throw new RuntimeException("MAL DAGGER");
        }
    }

    public Observable<Data> getData() {
        return belatrixsfApi.getData()
                .observeOn(Schedulers.newThread())
                .doOnNext(new Action1<Data>() {
                    @Override
                    public void call(Data data) {
                        Log.d("MainG", "trying to save data...");
                        if (data != null) {
                            localCache.save(LocalCache.LAST_BELATRIX_DATA, data);
                            Log.d("MainG", "Data Saved");
                        } else {
                            throw new RuntimeException("Data delivered by api is null");
                        }
                    }
                }).onErrorReturn(new Func1<Throwable, Data>() {
                    @Override
                    public Data call(Throwable throwable) {
                        Log.d("MainGApiError", ""+throwable.getMessage());
                        Log.d("MainGCacheFallback", "Fetching data stored in db");
                        return localCache.read(LocalCache.LAST_BELATRIX_DATA, new Data());
                    }
                });
    }

    public Observable<List<Item>> getItemList() {
        return getData().map(new Func1<Data, List<Item>>() {
            @Override
            public List<Item> call(Data data) {
                List<Item> list = new ArrayList<>();
                int i = 0;
                //Info
                list.add(new Item(String.valueOf(i++), data.getDream(), data.getDream()));
                list.add(new Item(String.valueOf(i++), data.getMyWhy(), data.getMyWhy()));
                list.add(new Item(String.valueOf(i++), data.getPeriod(), data.getPeriod()));
                list.add(new Item(String.valueOf(i++), data.getRoadmap(), data.getRoadmap()));
                //Metrics
                for (Data.Metric m: data.getMetric()) {
                    list.add(new Item(String.valueOf(i++), m.getType(), m.toString()));
                }
                //Volume
                for (Data.Volume v: data.getVolume()) {
                    list.add(new Item(String.valueOf(i++), v.getType(), v.toString()));
                }
                return list;
            }
        });
    }

    public void deleteCache(){
        localCache.delete(LocalCache.LAST_BELATRIX_DATA);
    }
}