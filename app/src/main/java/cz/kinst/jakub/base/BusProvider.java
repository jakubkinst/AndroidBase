package cz.kinst.jakub.base;

import com.squareup.otto.Bus;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class BusProvider {
    static final Bus bus = new Bus();

    public static Bus getBus() {
        return bus;
    }
}
