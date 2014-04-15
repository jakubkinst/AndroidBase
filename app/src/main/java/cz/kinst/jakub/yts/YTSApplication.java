package cz.kinst.jakub.yts;

import java.util.List;

import cz.kinst.jakub.androidbase.BaseApplication;

/**
 * Created by jakubkinst on 15/04/14.
 */
public class YTSApplication extends BaseApplication {

    @Override
    protected List<Object> getModules() {
        List<Object> modules = super.getModules();
        modules.add(new YTSModule());
        return modules;
    }
}
