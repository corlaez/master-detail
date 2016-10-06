package jarmandocordova.masterdetail.global.di.module;

import dagger.Module;
import dagger.Provides;
import io.paperdb.Paper;
import jarmandocordova.masterdetail.global.db.LocalCache;

/**
 * Created by jarma on 10/5/2016.
 */
@Module
public class LocalCacheModule {

    @Provides
    public LocalCache providesLocalCache(){
        return new LocalCache() {
            @Override
            public <T> void save(String key, T t) {
                Paper.book().write(key, t);
            }

            @Override
            public <T> T read(String key, T t) {
                return Paper.book().read(key, t);
            }

            @Override
            public void delete(String key) {
                Paper.book().delete(key);
            }
        };
    }

}
