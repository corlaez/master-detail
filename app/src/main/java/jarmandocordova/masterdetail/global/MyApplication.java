package jarmandocordova.masterdetail.global;

import android.app.Application;

import io.paperdb.Paper;
import jarmandocordova.masterdetail.global.di.component.BaseComponent;
import jarmandocordova.masterdetail.global.di.component.DaggerBaseComponent;

/**
 * Created by jarma on 10/5/2016.
 */
public class MyApplication extends Application {
    static BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        baseComponent = DaggerBaseComponent.builder().build();
    }

    public static BaseComponent getBaseComponent(){
        return MyApplication.baseComponent;
    }

}
