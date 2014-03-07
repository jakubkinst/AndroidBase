package cz.kinst.jakub.base;

import android.util.Log;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class Logger {

    public static void logD(String message) {
        if (BaseApplication.isDebug()) {
            Log.d(Config.LOG_TAG, message);
        }
    }

    public static void logE(String message) {
        Log.e(Config.LOG_TAG, message);
    }
}
