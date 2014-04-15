package cz.kinst.jakub.androidbase.adapter;

import android.view.View;
import android.view.ViewGroup;

public abstract interface ViewProvider<T> {
    public abstract View getView(T item, View convertView, ViewGroup parent);
}