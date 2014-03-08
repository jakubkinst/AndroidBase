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

public class JKUtils {

	public interface FirstTimeListener {
		void onFirstTime();
	}

	public static String LOG_TAG = "jkutils";

	public static void logError(String msg) {
		Log.e(getTag(), msg);
	}

	public static void log(String msg) {
		Log.d(getTag(), msg);
	}

	static String getTag() {
		return LOG_TAG;
	}

	public static void toast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static int dpToPx(Context context, int dp) {
		Resources r = context.getResources();
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dp, r.getDisplayMetrics());
		return px;
	}

	public static float pxToDp(Context context, int px) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	public static void croutonSuccess(Activity context, String message) {
		Crouton.makeText(context, message, Style.CONFIRM).show();
	}

	public static void croutonSuccess(Activity context, int resource) {
		croutonSuccess(context, context.getString(resource));
	}

	public static void croutonError(Activity context, String message) {
		Crouton.makeText(context, message, Style.ALERT).show();
	}

	public static void croutonError(Activity context, int resource) {
		croutonError(context, context.getString(resource));
	}

	public static void croutonInfo(Activity context, String message) {
		Crouton.makeText(context, message, Style.INFO).show();
	}

	public static void croutonInfo(Activity context, int resource) {
		croutonInfo(context, context.getString(resource));
	}

	public static boolean isOnline(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	public static SharedPreferences getPrefs(Context c) {
		return PreferenceManager.getDefaultSharedPreferences(c);
	}

	@SuppressLint("NewApi")
	public static boolean showChangelog(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		int currentVersion;
		try {
			currentVersion = context.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							PackageManager.GET_CONFIGURATIONS).versionCode;
		} catch (final NameNotFoundException e) {
			currentVersion = 0;
		}
		// new version info
		final int lastVersion = prefs.getInt("lastVersion", -1);
		if (currentVersion > lastVersion) {
			final Editor e = prefs.edit().putInt("lastVersion", currentVersion);
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
				e.apply();
			} else
				e.commit();
			return true;
		}
		return false;
	}

	public static void checkFirstTime(Context context,
			FirstTimeListener firstTimeListener) {
		SharedPreferences p = PreferenceManager
				.getDefaultSharedPreferences(context);
		if (!p.contains("jk-firsttime")) {
			p.edit().putBoolean("jk-firsttime", true).commit();
			firstTimeListener.onFirstTime();
		}
	}

	public static Point getScreenSize(Context c) {
		WindowManager wm = (WindowManager) c
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point(display.getWidth(), display.getHeight());
		return size;
	}

	public static String md5Java(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8")); // converting
																// byte array to
																// Hexadecimal
																// String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
		} catch (NoSuchAlgorithmException ex) {
		}
		return digest;
	}

}
