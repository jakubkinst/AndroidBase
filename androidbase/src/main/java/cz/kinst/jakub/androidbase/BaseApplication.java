package cz.kinst.jakub.androidbase;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;


/**
 * Created by jakubkinst on 07/03/14.
 */
public class BaseApplication extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        List<Object> modules = new ArrayList<Object>();
        modules.add(new AndroidModule(this));
        return modules;
    }

    public void inject(Object object) {
        graph.inject(object);
    }

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}