package cz.kinst.jakub.androidbase.utils;

import android.util.Log;

import cz.kinst.jakub.androidbase.BaseApplication;
import cz.kinst.jakub.androidbase.BaseConfig;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class Logger {

    public static void logD(String message) {
        if (BaseApplication.isDebug()) {
            Log.d(BaseConfig.LOG_TAG, message);
        }
    }

    public static void logE(String message) {
        Log.e(BaseConfig.LOG_TAG, message);
    }
}
