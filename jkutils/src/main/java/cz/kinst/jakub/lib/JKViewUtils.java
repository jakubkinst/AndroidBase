package cz.kinst.jakub.lib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class JKViewUtils {

	public static void startLoadingView(View v,Context context){
		ProgressBar loading = new ProgressBar(context);
		loading.setIndeterminate(true);
		loading.setLayoutParams(v.getLayoutParams());
		loading.setTag("loading_"+v.getId());
		v.setVisibility(View.GONE);
		((ViewGroup) v.getParent()).addView(loading);		
	}
	
	public static void stopLoadingView(View v,Context context){
		v.setVisibility(View.VISIBLE);
		((ViewGroup) v.getParent()).removeView(((ViewGroup) v.getParent()).findViewWithTag("loading_"+v.getId()));		
	}
}
