package cz.kinst.jakub.androidbase.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import cz.kinst.jakub.androidbase.BaseApplication;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BaseActivity extends FragmentActivity {

    @Inject
    protected Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init Dagger inject
        ((BaseApplication) getApplication()).inject(this);
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
