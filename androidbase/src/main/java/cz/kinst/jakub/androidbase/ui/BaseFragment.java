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
}
