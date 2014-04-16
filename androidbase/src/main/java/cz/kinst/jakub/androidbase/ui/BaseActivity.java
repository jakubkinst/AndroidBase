package cz.kinst.jakub.androidbase.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import cz.kinst.jakub.androidbase.BaseApplication;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

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


    public void croutonSuccess(CharSequence text) {
        Crouton.makeText(this, text, Style.CONFIRM).show();
    }

    public void croutonSuccess(int stringResId) {
        croutonSuccess(getString(stringResId));
    }

    public void croutonError(CharSequence text) {
        Crouton.makeText(this, text, Style.INFO).show();
    }

    public void croutonError(int stringResId) {
        croutonError(getString(stringResId));
    }

    public void croutonInfo(CharSequence text) {
        Crouton.makeText(this, text, Style.ALERT).show();
    }

    public void croutonInfo(int stringResId) {
        croutonInfo(getString(stringResId));
    }
}
