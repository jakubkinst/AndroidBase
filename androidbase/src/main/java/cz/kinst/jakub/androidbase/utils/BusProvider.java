package cz.kinst.jakub.androidbase.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BusProvider {
    static final Bus bus = new Bus(ThreadEnforcer.ANY);

    public static Bus getBus() {
        return bus;
    }
}
