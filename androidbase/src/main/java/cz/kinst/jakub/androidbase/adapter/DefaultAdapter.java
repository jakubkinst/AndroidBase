package cz.kinst.jakub.androidbase.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

public class DefaultAdapter<T> extends BaseAdapter {

    private List<T> mItems;
    private ViewProvider<T> mProvider;
    private boolean mReverse = false;

    public DefaultAdapter(List<T> items, ViewProvider<T> provider) {
        this.mItems = items;
        this.mProvider = provider;
    }

    public DefaultAdapter(List<T> items, ViewProvider<T> provider,
                          boolean reverse) {
        this(items, provider);

        Collections.reverse(this.mItems);
        this.mReverse = reverse;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mProvider.getView(getItem(position), convertView, parent);
    }

    public void addAll(List<T> items) {
        for (T t : items) {
            if (mReverse)
                mItems.add(0, t);
            else
                mItems.add(t);
        }
        notifyDataSetChanged();
    }

    public void add(T item) {
        if (mReverse)
            mItems.add(0, item);
        else
            mItems.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

}
