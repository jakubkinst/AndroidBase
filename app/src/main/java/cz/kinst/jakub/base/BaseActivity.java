package cz.kinst.jakub.base;

import android.support.v4.app.FragmentActivity;

import com.squareup.otto.Bus;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BaseActivity extends FragmentActivity {

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
