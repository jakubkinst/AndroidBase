package cz.kinst.jakub.lib;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class JKDebug {

	public static JKConfig config;

	public static void init(JKConfig config) {
		JKDebug.config = config;
	};

	public static String LOG_TAG = "jkutils";

	public static void logError(String msg) {
		if (config.getLogLevel() <= JKConfig.LOG_LEVEL_ERROR)
			Log.e(getTag(), msg);
	}

	public static void log(String msg) {
		if (config.getLogLevel() <= JKConfig.LOG_LEVEL_DEBUG)
			Log.d(getTag(), msg);
	}

	static String getTag() {
		return config.getLogTag();
	}

}
