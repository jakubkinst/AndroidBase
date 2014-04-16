package cz.kinst.jakub.androidbase.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import cz.kinst.jakub.androidbase.BaseApplication;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BaseFragment extends Fragment {
    @Inject
    protected Bus mBus;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        mBus.unregister(this);
        super.onStop();
    }

    public void croutonSuccess(CharSequence text) {
        ((BaseActivity) getActivity()).croutonSuccess(text);
    }

    public void croutonSuccess(int stringResId) {
        ((BaseActivity) getActivity()).croutonSuccess(getString(stringResId));
    }

    public void croutonError(CharSequence text) {
        ((BaseActivity) getActivity()).croutonError(text);
    }

    public void croutonError(int stringResId) {
        ((BaseActivity) getActivity()).croutonError(getString(stringResId));
    }

    public void croutonInfo(CharSequence text) {
        ((BaseActivity) getActivity()).croutonInfo(text);
    }

    public void croutonInfo(int stringResId) {
        ((BaseActivity) getActivity()).croutonInfo(getString(stringResId));
    }
}
