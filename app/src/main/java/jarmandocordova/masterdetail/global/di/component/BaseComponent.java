package jarmandocordova.masterdetail.global.di.component;

import javax.inject.Singleton;

import dagger.Component;
import jarmandocordova.masterdetail.global.data.belatrixsf.BelatrixsfApi;
import jarmandocordova.masterdetail.global.db.LocalCache;
import jarmandocordova.masterdetail.global.di.module.ApiModule;
import jarmandocordova.masterdetail.global.di.module.LocalCacheModule;
import jarmandocordova.masterdetail.global.di.module.NetworkModule;

/**
 * Created by jarma on 10/5/2016.
 */
@Singleton
@Component(modules = {LocalCacheModule.class, NetworkModule.class, ApiModule.class})
public interface BaseComponent {
    BelatrixsfApi getBelatrixsfApi();
    LocalCache getLocalCache();
}