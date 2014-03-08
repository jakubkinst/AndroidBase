package cz.kinst.jakub.lib;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class JKAdapter<T> extends ArrayAdapter<T> {	
	
	private int resourceViewId;

	public JKAdapter(Context context, List<T> objects) {
		super(context, android.R.layout.simple_list_item_1,objects);
	}
	
	public JKAdapter(Context context, int textViewResourceId, List<T> objects) {
		super(context, textViewResourceId, objects);
		this.resourceViewId = textViewResourceId;
	}
	
	public int getResourceViewId() {
		return resourceViewId;
	}

}
