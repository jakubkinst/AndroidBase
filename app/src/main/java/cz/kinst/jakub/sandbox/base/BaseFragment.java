package cz.kinst.jakub.sandbox.base;

import android.support.v4.app.Fragment;

import com.squareup.otto.Bus;

import cz.kinst.jakub.base.BusProvider;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BaseFragment extends Fragment{
    protected Bus mBus = BusProvider.getBus();

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
