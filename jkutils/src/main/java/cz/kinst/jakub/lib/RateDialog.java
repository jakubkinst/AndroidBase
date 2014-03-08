package cz.kinst.jakub.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

public class RateDialog {

	public static void checkRate(Context mContext, int launchesUntilPrompt,
			int daysUntilPrompt, String rateText,
			String neverText, String notNowText, Integer layoutRes) {
		final SharedPreferences prefs = mContext.getSharedPreferences(
				"apprater", 0);
		if (prefs.getBoolean("dontshowagain", false)) {
			return;
		}

		final SharedPreferences.Editor editor = prefs.edit();

		// Increment launch counter
		final long launch_count = prefs.getLong("launch_count", 0) + 1;
		editor.putLong("launch_count", launch_count);

		// Get date of first launch
		Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
		if (date_firstLaunch == 0) {
			date_firstLaunch = System.currentTimeMillis();
			editor.putLong("date_firstlaunch", date_firstLaunch);
		}

		// Wait at least n days before opening
		if (launch_count >= launchesUntilPrompt) {
			if (System.currentTimeMillis() >= date_firstLaunch
					+ (daysUntilPrompt * 24 * 60 * 60 * 1000)) {
				showRateDialog(mContext, editor, rateText, neverText,
						notNowText, layoutRes);
			}
		}

		editor.commit();
	}

	public static void showRateDialog(final Context context,
			final SharedPreferences.Editor editor,
			String rateText, String neverText, String notNowText,
			Integer layoutRes) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		final View v = inflater.inflate(layoutRes, null);

		final AlertDialog.Builder dialog = new AlertDialog.Builder(context)
				.setView(v)
				.setPositiveButton(rateText,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								context.startActivity(new Intent(
										Intent.ACTION_VIEW,
										Uri.parse("market://details?id="
												+ context.getPackageName())));
								editor.putBoolean("dontshowagain", true);
								editor.commit();
								dialog.dismiss();
							}
						})

				.setNegativeButton(notNowText,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {

								dialog.dismiss();
							}
						})
				.setNeutralButton(neverText,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int whichButton) {
								editor.putBoolean("dontshowagain", true);
								editor.commit();
								dialog.dismiss();
							}
						});
		dialog.create().show();
	}
}