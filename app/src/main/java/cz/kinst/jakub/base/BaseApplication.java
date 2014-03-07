package cz.kinst.jakub.base;

import android.app.Application;

import cz.kinst.jakub.sandbox.BuildConfig;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BaseApplication extends Application {

    public static boolean isDebug(){
        return BuildConfig.DEBUG;
    }
}