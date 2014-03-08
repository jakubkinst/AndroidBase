package cz.kinst.jakub.lib;

import android.view.View;
import android.view.ViewGroup;

public abstract interface ViewProvider<T> {
	public abstract View getView(T item, View convertView, ViewGroup parent);
}